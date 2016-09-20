/*
 * LoadParamsMBean.java
 *
 * Created on 16 januari 2008, 22:20
 */
package nl.verheulconsultants.syncmanpoc;

/**
 * Interface LoadParamsMBean
 *
 * @author Erik Verheul
 */
public interface LoadParamsMBean {

    /**
     *
     */
    public void setDefaultdLoad();

    /**
     *
     * @param fixedLoad
     */
    public void setFixedLoad(int fixedLoad);

    /**
     *
     * @param randomLoad
     */
    public void setRandomLoad(int randomLoad);

    /**
     *
     * @return
     */
    public int getFixedLoad();

    /**
     *
     * @return
     */
    public int getRandomLoad();
}
