/*
 * FifoMBean.java
 *
 * Created on 13 december 2007, 21:14
 */
package nl.verheulconsultants.syncmanpoc;

/**
 * Interface FifoMBean
 *
 * @author Erik Verheul
 */
public interface FifoMBean {

    /**
     *
     * @param size
     */
    public void setSize(int size);

    /**
     *
     * @return
     */
    public int getSize();

    /**
     *
     * @return
     */
    public String showOutput();
}


