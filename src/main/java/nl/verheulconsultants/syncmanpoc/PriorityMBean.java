/*
 * PriorityMBean.java
 *
 * Created on 17 januari 2008, 20:58
 */

package nl.verheulconsultants.syncmanpoc;

/**
 * Interface PriorityMBean
 *
 * @author erik
 */
public interface PriorityMBean
{    
    public void setPriority(int region, int prio);
    
    public String showPriorities();
}


