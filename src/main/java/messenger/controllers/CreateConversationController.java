package messenger.controllers;

import messenger.controllers.dto.ConversationDTO;
import messenger.data.ConversationRepository;
import messenger.domain.model.Conversation;
import messenger.domain.model.Participant;
import messenger.domain.model.Role;
import messenger.domain.model.User;
import messenger.service.ConversationService;
import messenger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by flogiston on 10.06.19.
 */
@Controller
@RequestMapping("/createConversation")
public class CreateConversationController {
    @Autowired
    private UserService userService;
    @Autowired
    private ConversationService conversationService;
    @GetMapping
    public ModelAndView showCreateForm(){
        ModelAndView mav = new ModelAndView("createConversation");
        ConversationDTO conversationDTO = new ConversationDTO();
        mav.addObject("conversationDTO", conversationDTO);
        return mav;
    }

    @PostMapping
    public ModelAndView saveConversation(@ModelAttribute ConversationDTO conversationDTO,
                                         BindingResult result){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User currentUser = userService.findByUserName(userName);

        Conversation conversation = conversationService.findByName(conversationDTO.getTitle());
        if(conversation != null){
            result.reject("Conversation with the same title already exists");
        }
        if(result.hasErrors()){
            return new ModelAndView("createConversation");
        }
        Participant participant = new Participant();

        conversationDTO.setCreator(currentUser);
        return new ModelAndView("/main_page");
    }
}
