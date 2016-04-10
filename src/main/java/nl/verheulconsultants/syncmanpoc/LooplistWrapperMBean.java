/*
 * LooplistWrapperMBean.java
 *
 * Created on 12 december 2007, 20:39
 */
package nl.verheulconsultants.syncmanpoc;

/**
 * Interface LoopsMBean
 *
 * @author erik
 */
public interface LooplistWrapperMBean {

    /**
     *
     * @param region
     */
    public void suspendRegion(int region);

    /**
     *
     * @param region
     */
    public void resumeRegion(int region);

    /**
     *
     * @return
     */
    public String showSuspended();

    /**
     *
     * @return
     */
    public String showActive();
}


