package messenger.controllers;

import messenger.data.ConversationRepository;
import messenger.data.UserRepository;
import messenger.domain.model.Conversation;
import messenger.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by flogiston on 01.06.19.
 */
@Controller
@RequestMapping("/main_page")
public class MainController {
    @Autowired
    private ConversationRepository conversationRepository;
    @Autowired
    private UserRepository userRepository;
    private User currentUser;
    @GetMapping
    public String loadConversations(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        System.out.println(userName);
        currentUser = userRepository.getByUserName(userName);
        List<Conversation> conversations = conversationRepository.getByCreator(currentUser);
        model.addAttribute("conversations", conversations);
        return "main_page";
    }
}
