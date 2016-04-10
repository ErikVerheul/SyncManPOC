/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.verheulconsultants.syncmanpoc;

import java.util.Random;

/**
 * For an explanation of the suspend / resume implementation see
 * http://java.sun.com/javase/6/docs/technotes/guides/concurrency/threadPrimitiveDeprecation.html
 * @author erik
 */
class MessageLoop implements Runnable {

    private final int region;
    private boolean suspended = false;
    private final Interval interval;
    private final Priority priorities;
    private final OutputQueue outputQueue;
    private final LoadParams params;
    private final Random r = new Random();
    private TimerResult tr = null;

    public MessageLoop(int region, Interval intervalMBean, Priority priorities, LoadParams params, OutputQueue outputQueue) {
        this.region = region;
        this.interval = intervalMBean;
        this.priorities = priorities;
        this.params = params;
        this.outputQueue = outputQueue;
    }

    public synchronized void suspend() { // findbugs asked to synchronized
        suspended = true;
    }

    public synchronized void resume() {
        if (suspended) {
            suspended = false;
            notify();
        }
    }

    public synchronized boolean isSuspended() { // findbugs asked to synchronized
        return suspended;
    }

    /*
     * Use the params settings to simulate the duration of the processing of a message.
     */
    private int simulateProcessing() {
        int procTime = params.getFixedLoad() + r.nextInt(params.getRandomLoad());
        try {
            Thread.sleep(procTime);
        } catch (InterruptedException e) {
            outputQueue.add(Thread.currentThread().getName() + " " +
                    "MessageLoop.simulateProcessing() was interrupted! " + e);
        }
        return procTime;
    }

    private void sleepToPriority() {
        /*
         * When prioriy = 0 do not sleep (highest priority)
         * When prioriy = 1 do half the throughput for this region.
         * When prioriy = 2 do deminisch the throughput to a third.
         * etc.
         * Note For the JMX user the highest priority is 1 (this.priority + 1) 
         * etc.
         * Assume that on average the processing + idle time equals the set interval.
         * When the processing time is longer than expected (the set interval) the 
         * prioritySleepTime will be to short.
         */
        try {
            // Sleep according to priority (0= highest)
            long prioritySleepTime = Syncman2POC.aantalRegios *
                    interval.getInterval() *
                    priorities.getPriority(region);
            Thread.sleep(prioritySleepTime);
        } catch (InterruptedException e) {
            outputQueue.add(Thread.currentThread().getName() + " " +
                    "Sleeping prioritySleepTime was interrupted! " + e);
        }
    }

    /*
     * Wait while this thread is suspended (Region is stopped).
     * Sleep a calculated time if a priority lower then max is set.
     * Sleep a calculated time to simulate processing.
     * Sleep as long as needed to not overload the (imaginary) Personenserver.
     */
    private TimerResult controlThroughput() {
        // Wait while this thread is suspended (Region is stopped).
        synchronized (this) {
            while (suspended) {
                try {
                    wait();
                } catch (InterruptedException ex) {
                    outputQueue.add(Thread.currentThread().getName() + " " +
                            "Waiting while suspended was interrupted! " + ex);
                    return new TimerResult(0L, 0L, 0L);
                }
            }
        }       
        // Sleep a calculated time if a priority lower then max is set.
        sleepToPriority();
        // Sleep a calculated time to simulate processing.
        int procTime = simulateProcessing();
        // Sleep as long as needed to not overload the (imaginary) Personenserver and return.
        return Utilities.timeOut(procTime, interval.getInterval());
    }

    public void run() {
        MessageSupplier ms = new MessageSupplier();
        while (true) {
            tr = controlThroughput();
            //Process time= the time simulateProcessing() used up
            //Sleep time=   the time controlThroughput() to adjust the message output throughput
            //Time elapsed= the actual time between the last 2 messages
            String out = "Process time= " + tr.procTime + ", Sleep time= " + tr.sleepTime +
                    ", Time elapsed= " + tr.elapsedTime + " " + ms.getNextMessage();
            //Add to FiFo queue
            outputQueue.add(Thread.currentThread().getName() + " " + out);
            //Print a message
            Utilities.threadMessage(out);
        }
    }
}
