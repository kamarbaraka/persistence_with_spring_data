package repositories;

import org.springframework.data.repository.CrudRepository;
import persistent_objects.Message;

/**
 * an interface to represent a repository of Message entities,
 * that extends the CrudRepository to enable CRUD operations.
 * @author kamar baraka*/

public interface MessageRepository
    extends CrudRepository<Message, Long> {
}
