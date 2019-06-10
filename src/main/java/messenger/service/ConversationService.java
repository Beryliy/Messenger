package messenger.service;

import messenger.controllers.dto.ConversationDTO;
import messenger.domain.model.Conversation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by flogiston on 10.06.19.
 */
public interface ConversationService {
    Conversation findByName(String name);
    void save(ConversationDTO conversationDTO);
}

