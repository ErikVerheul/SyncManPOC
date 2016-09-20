package nl.verheulconsultants.syncmanpoc;

import java.util.logging.Logger;

/*
 * Class Interval.java wraps the set interval period.
 * The interval period is the minimum time between two (imaginary) messages to the
 * Personenserver. This to prevent an overload of the Personenserver.
 *
 * Created on 11 december 2007, 15:19
 *
 * @author Erik Verheul
 */

/**
 *
 * @author Erik
 */

public class Interval implements IntervalMBean {
    private static final Logger LOG = Logger.getLogger(Interval.class.getName());

    private long interval;
    private static final long MIN_INTERVAL = 100;
    private static final long MAX_INTERVAL = 10_000;

    /**
     *
     */
    public Interval() {
        interval = 1_000;
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
     */
    @Override
    public void setInterval(long waitMilis) {
        if (waitMilis < MIN_INTERVAL || waitMilis > MAX_INTERVAL) {
            throw new IllegalArgumentException("Interval range must be >= " + MIN_INTERVAL + " and <= " + MAX_INTERVAL);
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
}


