package nl.verheulconsultants.syncmanpoc;

import java.util.logging.Logger;

/*
 * Class Interval.java wraps the set interval period.
 * The interval period is the minimum time between two (imaginary) messages to the
 * Personenserver. This to prevent an overload of the Personenserver.
 *
 * Created on 11 december 2007, 15:19
 *
 * @author erik
 */

/**
 *
 * @author Erik
 */

public class Interval implements IntervalMBean {

    private long interval;
    private final long minInterval = 100;
    private final long maxInterval = 10000;

    /**
     *
     */
    public Interval() {
        interval = 1000;
    }

    /**
     *
     * @param value
     */
    public Interval(long value) {
        interval = value;
    }

    /**
     *
     * @param waitMilis
     * @throws IllegalArgumentException
     */
    @Override
    public void setInterval(long waitMilis) throws IllegalArgumentException {
        if (waitMilis < minInterval || waitMilis > maxInterval) {
            throw new IllegalArgumentException("Interval range must be >= " + minInterval + " and <= " + maxInterval);
        }
        interval = waitMilis;
    }

    /**
     *
     * @return
     */
    @Override
    public long getInterval() {
        return interval;
    }
    private static final Logger LOG = Logger.getLogger(Interval.class.getName());
}


