package nl.verheulconsultants.syncmanpoc;

import edu.umd.cs.findbugs.annotations.SuppressWarnings;


public class Utilities {

    /* These variables are initiated once only at class loading.
     * lastFired is the point in time when the simulated processing came to an end
     * and the result is (imaginary) send to the Personenserver. The time between two
     * such sends must be equal or longer then the set interval time.
     */
    private static long lastFired = System.currentTimeMillis();
    private static long now = System.currentTimeMillis();
    /* Display a message, preceded by the name of the current thread */
    public static void threadMessage(String message) {
        String threadName = Thread.currentThread().getName();
        System.out.format("%s: %s%n", threadName, message);
    }

    /*
     * timeOut sleeps the period waitMillis or not at all when that time has passed (> now - lastFired).
     * The timeout time should be interval time * number of running threads minus procTime or 0 if negative.
     * This assumption is true if any other processing time is zero. As this assumption is certainly false
     * this routine uses a stored lastFired time to remember at what time the last message was processed.
     *
     * Assume that since lastFired some processing time is spend. If this period of processing
     * is shorter than the set interval, extra sleeptime is introduced in order not to overload
     * the Personenserver.
     * Note that the value of variable lastFired is saved between calls of this method.
     * The timeOut method is synchronized to force multiple threads calling this method to be serialized.
     */
    @SuppressWarnings(value = "SWL_SLEEP_WITH_LOCK_HELD")
    public static synchronized TimerResult timeOut(long procTime, long waitMillis) {
        now = System.currentTimeMillis();
        long elapsed;
        long idleTime = 0;
        if (now - lastFired < waitMillis) {
            try {
                idleTime = lastFired + waitMillis - now;
                //It is the intention to sleep with the lock held to simulatie a busy process.
                Thread.sleep(idleTime); 
            } catch (InterruptedException e) {
                Utilities.threadMessage("SyncTimer.timeOut was interrupted! " + e);
            }
        }
        now = System.currentTimeMillis();
        elapsed = now - lastFired;
        lastFired = now;
        return new TimerResult(procTime, idleTime, elapsed);
    }
}
