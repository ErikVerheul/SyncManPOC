/*
 * Priority.java
 *
 * Created on 17 januari 2008, 20:58
 */
package nl.verheulconsultants.syncmanpoc;

import edu.umd.cs.findbugs.annotations.SuppressWarnings;

/**
 * Class Priority
 *
 * @author erik
 */
public class Priority implements PriorityMBean {

    private int maxPriority = 1;
    private int minPriority = 10;
    private int priorities[] = new int[Syncman2POC.aantalRegios];

    public Priority() {
        for (int i = 0; i < priorities.length; i++) {
            priorities[i] = maxPriority - 1;
        }
    }

    protected int getPriority(int region) {
        return priorities[region];
    }

    public void setPriority(int region, int prio) throws IllegalArgumentException {
        if (region < 1 || region > Syncman2POC.aantalRegios) {
            throw new IllegalArgumentException("Region range must be >= 1 and <= " + Syncman2POC.aantalRegios);
        }
        if (prio < maxPriority || prio > minPriority) {
            throw new IllegalArgumentException("Priority range must be >= " + maxPriority + " and <= " + minPriority);
        }
        priorities[region - 1] = prio - 1;
    }

    @SuppressWarnings("SBSC_USE_STRINGBUFFER_CONCATENATION")
    public String showPriorities() {
        String s = "Set priorities<br>";
        for (int i = 0; i < priorities.length; i++) {
            s = s + "Region" + (i + 1) + ": priority= " + (priorities[i] + 1) + "<br>";
        }
        return s;
    }
}


