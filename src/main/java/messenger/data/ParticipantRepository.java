package messenger.data;

import messenger.domain.model.Conversation;
import messenger.domain.model.Participant;
import messenger.domain.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by flogiston on 10.06.19.
 */
public interface ParticipantRepository extends CrudRepository<Participant, Integer> {
    Participant save(Participant participant);
    Participant findByUserAndConversation(User user, Conversation conversation);
    List<Participant> findByUser(User user);
    List<Participant> findByConversation(Conversation conversation);
    long deleteByUserAndConversation(User user, Conversation conversation);
}
