/*
 * Loops.java
 *
 * Created on 12 december 2007, 20:39
 */
package nl.verheulconsultants.syncmanpoc;

import edu.umd.cs.findbugs.annotations.SuppressWarnings;
import java.util.List;
import java.util.logging.Logger;

/**
 * Class LooplistWrapper is a wrapper around the List of MessageLoop instances.
 * Using a JMX client you can suspend and resume individual region processing.
 * The list op suspended and a list of active regions can be requested.
 *
 * @author Erik Verheul
 */
public class LooplistWrapper implements LooplistWrapperMBean {
    private static final Logger LOG = Logger.getLogger(LooplistWrapper.class.getName());

    private final List<MessageLoop> loops;

    /**
     *
     * @param loops
     */
    public LooplistWrapper(List loops) {
        this.loops = loops;
    }

    /**
     *
     * @param region
     * @throws IllegalArgumentException
     */
    @Override
    public void suspendRegion(int region) throws IllegalArgumentException {
        if (region < 1 || region > loops.size() || loops.get(region - 1).isSuspended()) {
            throw new IllegalArgumentException("Region range must be active and >= 1 and <= " + loops.size());
        }
        loops.get(region - 1).suspend();
    }

    /**
     *
     * @param region
     * @throws IllegalArgumentException
     */
    @Override
    public void resumeRegion(int region) throws IllegalArgumentException {
        if (region < 1 || region > loops.size() || !loops.get(region - 1).isSuspended()) {
            throw new IllegalArgumentException("Region range must be suspended and >= 1 and <= " + loops.size());
        }
        loops.get(region - 1).resume();
    }

    /**
     *
     * @return
     */
    @SuppressWarnings("SBSC_USE_STRINGBUFFER_CONCATENATION")
    @Override
    public String showSuspended() {
        String s = "Suspended regions<br>";
        for (int i = 0; i < loops.size(); i++) {
            if (loops.get(i).isSuspended()) {
                s = s + (i + 1) + "<br>";
            }
        }
        return s;
    }

    /**
     *
     * @return
     */
    @SuppressWarnings("SBSC_USE_STRINGBUFFER_CONCATENATION")
    @Override
    public String showActive() {
        String s = "Active regions<br>";
        for (int i = 0; i < loops.size(); i++) {
            if (!loops.get(i).isSuspended()) {
                s = s + (i + 1) + "<br>";
            }
        }
        return s;
        }
    }


