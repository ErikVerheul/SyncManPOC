
package nl.verheulconsultants.syncmanpoc;

import java.util.logging.Logger;

/**
 * Class Fifo
 *
 * @author erik
 */
public class Fifo implements FifoMBean {

    OutputQueue outputQueue = null;

    /**
     *
     */
    public Fifo() {
    }

    /**
     *
     * @param outputQueue
     */
    public Fifo(OutputQueue outputQueue) {
        this.outputQueue = outputQueue;
    }

    /**
     *
     * @param size
     * @throws IllegalArgumentException
     */
    @Override
    public void setSize(int size) throws IllegalArgumentException {
        outputQueue.setSize(size);
    }

    /**
     *
     * @return
     */
    @Override
    public int getSize() {
        return outputQueue.getSize();
    }

    /**
     *
     * @return
     */
    @Override
    public String showOutput() {
        String comment = "Process time = the time the simulation of the processing of one message used up<br>" +
                "Sleep time = the time to adjust the message output throughput<br>" +
                "Time elapsed = the actual time between the last 2 messages<br><br>";
        return comment + outputQueue.getAll();
    }
    private static final Logger LOG = Logger.getLogger(Fifo.class.getName());
}


