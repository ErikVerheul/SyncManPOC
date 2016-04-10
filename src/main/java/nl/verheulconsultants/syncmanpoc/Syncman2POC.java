package nl.verheulconsultants.syncmanpoc;

/**
 * Note that the JMX output of this application is optimized for web access.
 * When needed <br> line breaks are added.
 * Note that the port number to access the application is changed to 81
 * @author erik
 */
import com.sun.jdmk.comm.AuthInfo;
import com.sun.jdmk.comm.HtmlAdaptorServer;
import javax.management.*;
import java.lang.management.*;
import java.util.*;

public class Syncman2POC {

    protected final static int aantalRegios = 25;
    private final List<MessageLoop> looplist = new ArrayList();
    private MBeanServer mbs = null;
    private Interval interval = null;
    private LooplistWrapper loops = null;
    private Fifo fifo = null;
    private Priority priorities = null;
    private LoadParams params = null;
    private final OutputQueue outputQueue = new OutputQueue();

    /**
     * Start a thread for each region. All threads refer to the same one and only instances of Interval, Priority, LoadParams and OutputQueue. A change
     * introduced with JMX to such an instance is applicable to all threads.
     * <aantalRegios> instances of MessageLoop are instantiated.
     */
    private void startThreads() {
        for (int i = 0; i < aantalRegios; i++) {
            MessageLoop loop = new MessageLoop(i, interval, priorities, params, outputQueue);
            looplist.add(loop);
            Thread t = new Thread(loop);
            t.setName("Region " + (i + 1));
            t.start();
        }
    }

    private Syncman2POC() {
        // Create an MBeanServer and HTML adaptor (J2SE 1.5)
        mbs = ManagementFactory.getPlatformMBeanServer();
        HtmlAdaptorServer adapter = new HtmlAdaptorServer(81);
        adapter.addUserAuthenticationInfo(new AuthInfo("Erik", "Verheul"));

        // Unique identification of MBeans
        interval = new Interval();
        loops = new LooplistWrapper(looplist);
        priorities = new Priority();
        fifo  = new Fifo(outputQueue);
        params = new LoadParams();
        ObjectName registerName;
        ObjectName adapterName;

        try {
            // Uniquely identify the MBeans and register them with the MBeanServer 
            registerName = new ObjectName("Syncman2POC:name=set speed");
            mbs.registerMBean(interval, registerName);
            registerName = new ObjectName("Syncman2POC:name=suspend/resume regions");
            mbs.registerMBean(loops, registerName);
            registerName = new ObjectName("Syncman2POC:name=set priorities for regions");
            mbs.registerMBean(priorities, registerName);
            registerName = new ObjectName("Syncman2POC:name=show output");
            mbs.registerMBean(fifo, registerName);
            registerName = new ObjectName("Syncman2POC:name=set load parameters");
            mbs.registerMBean(params, registerName);
            // Register and start the HTML adaptor            
            adapterName = new ObjectName("nl.verheulconsultants.Syncman2POC:name=htmladapter,port=81 (do not change the port number!)");
            mbs.registerMBean(adapter, adapterName);
            adapter.start();
        } catch (MalformedObjectNameException | InstanceAlreadyExistsException | MBeanRegistrationException | NotCompliantMBeanException e) {
            e.printStackTrace();
        }
        System.out.println("Syncmanager2 Proof Of Concept is running...");
        startThreads();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Syncman2POC();
    }
}
