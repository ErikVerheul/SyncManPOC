/*
 * Priority.java
 *
 * Created on 17 januari 2008, 20:58
 */
package nl.verheulconsultants.syncmanpoc;

import edu.umd.cs.findbugs.annotations.SuppressWarnings;
import java.util.logging.Logger;

/**
 * Class Priority
 *
 * @author Erik Verheul
 */
public class Priority implements PriorityMBean {
    private static final Logger LOG = Logger.getLogger(Priority.class.getName());

    private static final int MAX_PRIORITY = 1;
    private static final int MIN_PRIORITY = 10;
    private static final int PRIORITIES[] = new int[Syncman2POC.NR_OF_REGIONS];

    /**
     *
     */
    public Priority() {
        for (int i = 0; i < PRIORITIES.length; i++) {
            PRIORITIES[i] = MAX_PRIORITY - 1;
        }
    }

    /**
     *
     * @param region
     * @return
     */
    protected int getPriority(int region) {
        return PRIORITIES[region];
    }

    /**
     *
     * @param region
     * @param prio
     * @throws IllegalArgumentException
     */
    @Override
    public void setPriority(int region, int prio) throws IllegalArgumentException {
        if (region < 1 || region > Syncman2POC.NR_OF_REGIONS) {
            throw new IllegalArgumentException("Region range must be >= 1 and <= " + Syncman2POC.NR_OF_REGIONS);
        }
        if (prio < MAX_PRIORITY || prio > MIN_PRIORITY) {
            throw new IllegalArgumentException("Priority range must be >= " + MAX_PRIORITY + " and <= " + MIN_PRIORITY);
        }
        PRIORITIES[region - 1] = prio - 1;
    }

    /**
     *
     * @return
     */
    @SuppressWarnings("SBSC_USE_STRINGBUFFER_CONCATENATION")
    @Override
    public String showPriorities() {
        String s = "Set priorities<br>";
        for (int i = 0; i < PRIORITIES.length; i++) {
            s = s + "Region" + (i + 1) + ": priority= " + (PRIORITIES[i] + 1) + "<br>";
        }
        return s;
    }
}


