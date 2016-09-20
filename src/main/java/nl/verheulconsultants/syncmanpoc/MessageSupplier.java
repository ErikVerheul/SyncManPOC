/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.verheulconsultants.syncmanpoc;

import java.util.logging.Logger;

/**
 *
 * @author Erik Verheul
 */
public class MessageSupplier {
    private static final Logger LOG = Logger.getLogger(MessageSupplier.class.getName());

    int index = 0;
    String[] dummyMessage = {
        "Mares eat oats",
        "Does eat oats",
        "Little lambs eat ivy",
        "A kid will eat ivy too"
    };

    /**
     *
     * @return
     */
    public String getNextMessage() {
        String s = dummyMessage[index];
        index++;
        if (index >= dummyMessage.length) {
            index = 0;
        }
        return s;
    }
}
