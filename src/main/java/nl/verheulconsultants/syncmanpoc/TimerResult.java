/*
 * class TimerResult is a DOA to export timer results.
 */

package nl.verheulconsultants.syncmanpoc;

/**
 *
 * @author erik
 */
public class TimerResult {
    long procTime;      // the duration of the simulated processing
    long sleepTime;     // sleep time calculated in last timeout in ms.
    long elapsedTime;   // time passed since last timeout in ms.
    
    public TimerResult(long procTime,long idleTime, long elapsedTime) {
        this.procTime = procTime;
        this.sleepTime = idleTime;
        this.elapsedTime = elapsedTime;
    }
    
}
