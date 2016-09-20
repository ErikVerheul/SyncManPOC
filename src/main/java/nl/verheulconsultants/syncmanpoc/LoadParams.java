/*
 * LoadParams.java
 *
 * Created on 16 januari 2008, 22:20
 */
package nl.verheulconsultants.syncmanpoc;

import java.util.logging.Logger;

/**
 * Class LoadParams simulates the processing of a message bij calculating a sleep
 * period for the worker thread base on several paramaters. These parameters can
 * be changed using a JMX client.
 *
 * @author Erik Verheul
 */
public final class LoadParams implements LoadParamsMBean {
    private static final Logger LOG = Logger.getLogger(LoadParams.class.getName());

    private int fixedLoad;
    private int randomLoad;
    private static final int MIN_FIXEDLOAD = 0;
    private static final int MIN_RANDOMLOAD = 0;

    /**
     *
     */
    public LoadParams() {
        setDefaultdLoad();
    }

    /**
     *
     */
    @Override
    public void setDefaultdLoad() {
        fixedLoad = 24_300;
        randomLoad = 1_000;
    }

    /**
     *
     * @param fixedLoad
     */
    @Override
    public void setFixedLoad(int fixedLoad) {
        if (fixedLoad < MIN_FIXEDLOAD) {
            throw new IllegalArgumentException("The fixed laod must be >=  " + MIN_FIXEDLOAD);
        }
        this.fixedLoad = fixedLoad;
    }

    /**
     *
     * @param randomLoad
     */
    @Override
    public void setRandomLoad(int randomLoad) {
        if (randomLoad < MIN_RANDOMLOAD) {
            throw new IllegalArgumentException("The random laod must be >=  " + MIN_RANDOMLOAD);
        }
        this.randomLoad = randomLoad;
    }

    /**
     *
     * @return
     */
    @Override
    public int getFixedLoad() {
        return fixedLoad;
    }

    /**
     *
     * @return
     */
    @Override
    public int getRandomLoad() {
        return randomLoad;
    }
}


