package nl.verheulconsultants.syncmanpoc;

/*
 * Class Interval.java wraps the set interval period.
 * The interval period is the minimum time between two (imaginary) messages to the
 * Personenserver. This to prevent an overload of the Personenserver.
 *
 * Created on 11 december 2007, 15:19
 *
 * @author erik
 */
public class Interval implements IntervalMBean {

    private long interval;
    private long minInterval = 100;
    private long maxInterval = 10000;

    public Interval() {
        interval = 1000;
    }

    public Interval(long value) {
        interval = value;
    }

    public void setInterval(long waitMilis) throws IllegalArgumentException {
        if (waitMilis < minInterval || waitMilis > maxInterval) {
            throw new IllegalArgumentException("Interval range must be >= " + minInterval + " and <= " + maxInterval);
        }
        interval = waitMilis;
    }

    public long getInterval() {
        return interval;
    }
}


