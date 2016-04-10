/*
 * Fifo.java
 *
 * Created on 13 december 2007, 21:14
 */
package nl.verheulconsultants.syncmanpoc;

import javax.management.*;

/**
 * Class Fifo
 *
 * @author erik
 */
public class Fifo implements FifoMBean {

    OutputQueue outputQueue = null;

    public Fifo() {
    }

    public Fifo(OutputQueue outputQueue) {
        this.outputQueue = outputQueue;
    }

    public void setSize(int size) throws IllegalArgumentException {
        outputQueue.setSize(size);
    }

    public int getSize() {
        return outputQueue.getSize();
    }

    public String showOutput() {
        String comment = "Process time = the time the simulation of the processing of one message used up<br>" +
                "Sleep time = the time to adjust the message output throughput<br>" +
                "Time elapsed = the actual time between the last 2 messages<br><br>";
        return comment + outputQueue.getAll();
    }
}


