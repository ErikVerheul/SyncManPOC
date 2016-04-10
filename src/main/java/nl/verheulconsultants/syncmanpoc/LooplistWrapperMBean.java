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

    public void suspendRegion(int region);

    public void resumeRegion(int region);

    public String showSuspended();

    public String showActive();
}


