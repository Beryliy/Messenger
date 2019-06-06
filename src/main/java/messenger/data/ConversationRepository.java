package messenger.data;

import messenger.domain.model.Conversation;
import messenger.domain.model.User;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by flogiston on 01.06.19.
 */
public interface ConversationRepository extends Repository<Conversation, Integer> {
    List<Conversation> getByCreator(User creator);
}
