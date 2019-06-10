package messenger.service;


import messenger.controllers.dto.ConversationDTO;
import messenger.data.ConversationRepository;
import messenger.data.ParticipantRepository;
import messenger.domain.model.Conversation;
import messenger.domain.model.Participant;
import messenger.domain.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by flogiston on 10.06.19.
 */
@Service
public class ConversationServiceImpl implements ConversationService{
    @Autowired
    private ConversationRepository conversationRepository;
    @Autowired
    private ParticipantRepository participantRepository;

    @Override
    public Conversation findByName(String name) {
        return null;
    }

    @Override
    public void save(ConversationDTO conversationDTO) {
        Conversation conversation = new Conversation();
        conversation.setTitle(conversationDTO.getTitle());
        conversationRepository.save(conversation);
        Participant participant = new Participant();
        participant.setUser(conversationDTO.getCreator());
        Set<Role> roles = new HashSet<>();
        roles.add(Role.USER);
        roles.add(Role.CREATOR);
        participant.setRoles(roles);
        participant.setConversation(conversation);
        participantRepository.save(participant);
    }
}
