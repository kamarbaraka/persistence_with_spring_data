package persistent_objects;

import configuration.SpringDataConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import repositories.MessageRepository;

import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import static org.junit.jupiter.api.Assertions.*;

/**
 * a test class for the Message class.
 * @author kamar baraka*/

/*extend the test with a spring extension to integrate spring-test context with junit5*/
@ExtendWith(SpringExtension.class)
/*configure test with the springDataConfiguration created earlier*/
@ContextConfiguration(classes = {SpringDataConfiguration.class})
class MessageTest {

    /*declare and inject the MessageRepository*/
    @Autowired
    private MessageRepository messageRepository;

    /**
     * tests persistence of the message entity*/
    @Test
    void messagePersistenceTest(){

        /*construct a Message and set its message value*/
        Message message = new Message();
        message.setTheMessage("Hello, today am trying out spring data jpa");

        /*persist the message object in the database*/
        messageRepository.save(message);

        /*retrieve the message from the database to confirm its persistence.
        * casting is necessary as findAll() returns iterable instead of a list*/
        List<Message> messages = (List<Message>) messageRepository.findAll();

        /*assert the persistence of the message object properties */
        assertAll(
                () -> assertEquals(1, messages.size()),
                () -> assertEquals("Hello, today am trying out spring data jpa", messages.get(0).getTheMessage())
        );

    }

}