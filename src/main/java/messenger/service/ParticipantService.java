package messenger.service;

import messenger.controllers.dto.ParticipantDTO;
import messenger.domain.model.Conversation;
import messenger.domain.model.Participant;
import messenger.domain.model.User;

import java.util.List;

/**
 * Created by flogiston on 11.06.19.
 */
public interface ParticipantService {
    void save(ParticipantDTO DTO);
    Participant findByUserAndConversation(User user, Conversation conversation);
    List<Participant> findByUser(User user);
    List<Participant> findByConversation(Conversation conversation);
    void deleteByUserAndConversation(User user, Conversation conversation);
}
