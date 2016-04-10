/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.verheulconsultants.syncmanpoc;

import java.util.*;
import java.util.concurrent.*;

/**
 *
 * @author erik
 */
public class OutputQueue {

    private int size = 10;
    private int maxSize = 1000;
    BlockingQueue<String> queue = new LinkedBlockingQueue();

    public OutputQueue() {
    }

    public OutputQueue(int size) {
        if (size > 0 && size <= maxSize) {
            this.size = size;
        }
    }

    private void trimSize(int size) {
        while (size < queue.size()) {
            queue.remove();
        }
    }

    public void setSize(int newSize) throws IllegalArgumentException {
        if (newSize <= 0 || newSize > maxSize) {
            throw new IllegalArgumentException("Output lines range must be >= 1 and <= " + maxSize);
        }
        size = newSize;
        trimSize(size);
    }

    public int getSize() {
        return size;
    }

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

    public String getAll() {
        StringBuilder buf = new StringBuilder(10000);
        Iterator<String> it = queue.iterator();
        while (it.hasNext()) {
            buf.append(it.next());
            buf.append("<br>");
        }
        return buf.toString();
    }
}
