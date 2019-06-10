package messenger.data;

import messenger.domain.model.Conversation;
import messenger.domain.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by flogiston on 01.06.19.
 */
public interface ConversationRepository extends CrudRepository<Conversation, Integer> {
    List<Conversation> findByCreator(User creator);
    Conversation findByName(String name);
    Conversation save(Conversation conversation);
}
