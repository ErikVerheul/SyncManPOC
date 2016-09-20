/*
 * class TimerResult is a DOA to export timer results.
 */

package nl.verheulconsultants.syncmanpoc;

import java.util.logging.Logger;

/**
 *
 * @author Erik Verheul 
 */
public class TimerResult {
    private static final Logger LOG = Logger.getLogger(TimerResult.class.getName());
    // the duration of the simulated processing
    long procTime;
    // sleep time calculated in last timeout in ms.
    long sleepTime;
    // time passed since last timeout in ms.
    long elapsedTime;   
    
    /**
     *
     * @param procTime
     * @param idleTime
     * @param elapsedTime
     */
    public TimerResult(long procTime,long idleTime, long elapsedTime) {
        this.procTime = procTime;
        this.sleepTime = idleTime;
        this.elapsedTime = elapsedTime;
    }
    
}
