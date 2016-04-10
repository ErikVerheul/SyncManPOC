/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.verheulconsultants.syncmanpoc;

/**
 *
 * @author ERIK
 */
public class MessageSupplier {

    int index = 0;
    String dummyMessage[] = {
        "Mares eat oats",
        "Does eat oats",
        "Little lambs eat ivy",
        "A kid will eat ivy too"
    };

    public String getNextMessage() {
        String s = dummyMessage[index];
        index++;
        if (index >= dummyMessage.length) {
            index = 0;
        }
        return s;
    }
}
