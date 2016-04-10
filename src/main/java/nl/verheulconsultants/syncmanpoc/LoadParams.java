/*
 * LoadParams.java
 *
 * Created on 16 januari 2008, 22:20
 */
package nl.verheulconsultants.syncmanpoc;

/**
 * Class LoadParams simulates the processing of a message bij calculating a sleep
 * period for the worker thread base on several paramaters. These parameters can
 * be changed using a JMX client.
 *
 * @author erik
 */
public class LoadParams implements LoadParamsMBean {

    private int fixedLoad;
    private int randomLoad;
    private int minFixedLoad = 0;
    private int minRandomLoad = 0;

    public LoadParams() {
        setDefaultdLoad();
    }

    public void setDefaultdLoad() {
        fixedLoad = 24300;
        randomLoad = 1000;
    }

    public void setFixedLoad(int fixedLoad) throws IllegalArgumentException {
        if (fixedLoad < minFixedLoad) {
            throw new IllegalArgumentException("The fixed laod must be >=  " + minFixedLoad);
        }
        this.fixedLoad = fixedLoad;
    }

    public void setRandomLoad(int randomLoad) throws IllegalArgumentException {
        if (randomLoad < minRandomLoad) {
            throw new IllegalArgumentException("The random laod must be >=  " + minRandomLoad);
        }
        this.randomLoad = randomLoad;
    }

    public int getFixedLoad() {
        return fixedLoad;
    }

    public int getRandomLoad() {
        return randomLoad;
    }
}


