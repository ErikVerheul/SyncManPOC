/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.verheulconsultants.syncmanpoc;

import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

/**
 *
 * @author Erik Verheul
 */
public class OutputQueue {
    private static final Logger LOG = Logger.getLogger(OutputQueue.class.getName());

    private int size = 10;
    private static final int MAX_SIZE = 1_000;
    BlockingQueue<String> queue = new LinkedBlockingQueue();

    /**
     *
     */
    public OutputQueue() {
        // ToDo: Can this method be removed?
    }

    /**
     *
     * @param size
     */
    public OutputQueue(int size) {
        if (size > 0 && size <= MAX_SIZE) {
            this.size = size;
        }
    }

    private void trimSize(int size) {
        while (size < queue.size()) {
            queue.remove();
        }
    }

    /**
     *
     * @param newSize
     * @throws IllegalArgumentException
     */
    public void setSize(int newSize) throws IllegalArgumentException {
        if (newSize <= 0 || newSize > MAX_SIZE) {
            throw new IllegalArgumentException("Output lines range must be >= 1 and <= " + MAX_SIZE);
        }
        size = newSize;
        trimSize(size);
    }

    /**
     *
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     *
     * @param s
     */
    public void add(String s) {
        while (queue.size() >= size) {
            queue.remove();
        }
        try {
            queue.put(s);
        } catch (InterruptedException e) {
            Utilities.threadMessage(s + " not added to queue, error: " + e);
        }
    }

    /**
     *
     * @return
     */
    public String getAll() {
        StringBuilder buf = new StringBuilder(10_000);
        Iterator<String> it = queue.iterator();
        while (it.hasNext()) {
            buf.append(it.next());
            buf.append("<br>");
        }
        return buf.toString();
    }
}
