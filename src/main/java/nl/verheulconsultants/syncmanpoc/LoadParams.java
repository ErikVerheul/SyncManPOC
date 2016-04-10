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
 * @author erik
 */
public final class LoadParams implements LoadParamsMBean {

    private int fixedLoad;
    private int randomLoad;
    private final int minFixedLoad = 0;
    private final int minRandomLoad = 0;

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
        fixedLoad = 24300;
        randomLoad = 1000;
    }

    /**
     *
     * @param fixedLoad
     * @throws IllegalArgumentException
     */
    @Override
    public void setFixedLoad(int fixedLoad) throws IllegalArgumentException {
        if (fixedLoad < minFixedLoad) {
            throw new IllegalArgumentException("The fixed laod must be >=  " + minFixedLoad);
        }
        this.fixedLoad = fixedLoad;
    }

    /**
     *
     * @param randomLoad
     * @throws IllegalArgumentException
     */
    @Override
    public void setRandomLoad(int randomLoad) throws IllegalArgumentException {
        if (randomLoad < minRandomLoad) {
            throw new IllegalArgumentException("The random laod must be >=  " + minRandomLoad);
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
    private static final Logger LOG = Logger.getLogger(LoadParams.class.getName());
}


