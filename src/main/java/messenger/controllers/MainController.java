package messenger.controllers;

import messenger.domain.model.Conversation;
import messenger.domain.model.Participant;
import messenger.domain.model.User;
import messenger.service.ConversationService;
import messenger.service.ParticipantService;
import messenger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by flogiston on 01.06.19.
 */
@Controller
@RequestMapping("/main_page")
public class MainController {
    @Autowired
    private UserService userService;
    @Autowired
    private ParticipantService participantService;
    @Autowired
    ConversationService conversationService;
    private User currentUser;
    @GetMapping
    public ModelAndView loadConversations(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        currentUser = userService.findByUserName(userName);
        List<Participant> participants = participantService.findByUser(currentUser);
        List<Conversation> conversations = new ArrayList<>();
        for(Participant participant : participants){
            conversations.add(participant.getConversation());
        }
        ModelAndView mav = new ModelAndView("main_page");
        mav.addObject("conversations", conversations);
        return mav;
    }
}
