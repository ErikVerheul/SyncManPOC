/*
 * IntervalMBean.java
 *
 * Created on 13 december 2007
 */
package nl.verheulconsultants.syncmanpoc;

/**
 *
 * @author erik
 */
public interface IntervalMBean {

    /**
     *
     * @param waitMilis
     */
    public void setInterval(long waitMilis);

    /**
     *
     * @return
     */
    public long getInterval();
}
