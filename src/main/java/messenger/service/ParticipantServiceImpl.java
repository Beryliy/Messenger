package messenger.service;

import messenger.controllers.dto.ParticipantDTO;
import messenger.data.ParticipantRepository;
import messenger.domain.model.Conversation;
import messenger.domain.model.Participant;
import messenger.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by flogiston on 11.06.19.
 */
@Service
public class ParticipantServiceImpl implements ParticipantService {
    @Autowired
    private ParticipantRepository participantRepository;
    @Override
    public void save(ParticipantDTO participantDTO) {
        Participant participant = new Participant();
        participant.setUser(participantDTO.getUser());
        participant.setConversation(participantDTO.getConversation());
        participant.setRoles(participantDTO.getRoles());
        participantRepository.save(participant);
    }

    @Override
    public Participant findByUserAndConversation(User user, Conversation conversation) {
        return participantRepository.findByUserAndConversation(user, conversation);
    }

    @Override
    public List<Participant> findByUser(User user) {
        return participantRepository.findByUser(user);
    }

    @Override
    public List<Participant> findByConversation(Conversation conversation) {
        return participantRepository.findByConversation(conversation);
    }

    @Override
    @Transactional
    public void deleteByUserAndConversation(User user, Conversation conversation) {
        participantRepository.deleteByUserAndConversation(user, conversation);
    }
}
