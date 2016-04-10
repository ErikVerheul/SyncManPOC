/*
 * LoadParamsMBean.java
 *
 * Created on 16 januari 2008, 22:20
 */

package nl.verheulconsultants.syncmanpoc;

/**
 * Interface LoadParamsMBean
 *
 * @author erik
 */
public interface LoadParamsMBean
{
    public void setDefaultdLoad();
    
    public void setFixedLoad(int fixedLoad);
    
    public void setRandomLoad(int randomLoad);
    
    public int getFixedLoad();
    
    public int getRandomLoad();        
}


