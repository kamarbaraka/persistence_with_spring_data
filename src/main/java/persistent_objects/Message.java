package persistent_objects;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * a class to represent a message entity.
 * @author kamar baraka.*/

public class Message {

    @Id
    @GeneratedValue
    private long messageId;

    private String theMessage;

    public long getMessageId() {
        return messageId;
    }

    public String getTheMessage() {
        return theMessage;
    }

    public void setTheMessage(String theMessage) {
        this.theMessage = theMessage;
    }
}
