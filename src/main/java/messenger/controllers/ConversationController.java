package messenger.controllers;

import messenger.controllers.dto.ParticipantDTO;
import messenger.domain.model.Conversation;
import messenger.domain.model.Participant;
import messenger.domain.model.Role;
import messenger.domain.model.User;
import messenger.service.ConversationService;
import messenger.service.ParticipantService;
import messenger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by flogiston on 11.06.19.
 */
@Controller
public class ConversationController {
    @Autowired
    UserService userService;
    @Autowired
    ParticipantService participantService;
    @Autowired
    ConversationService conversationService;
    private Conversation conversation;
    @RequestMapping(value = "/conversation", method = RequestMethod.GET)
    public ModelAndView showContent(HttpServletRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User currentUser = userService.findByUserName(userName);
        String conversationTitle = request.getParameter("title");
        conversation = conversationService.findByTitle(conversationTitle);
        Participant participant = participantService.findByUserAndConversation(currentUser, conversation);
        ModelAndView mav;
        if(participant.getRoles().contains(Role.CREATOR)){
            ParticipantDTO participantDTO = new ParticipantDTO();
            List<Participant> participants = participantService.findByConversation(conversation);
            List<User> users = new ArrayList<>();
            for(Participant p : participants){
                users.add(p.getUser());
            }
            mav = new ModelAndView();
            mav.addObject("participantDTO", participantDTO);
            mav.addObject("users", users);
        }else{
            mav = new ModelAndView("redirect:/chat?title=" + conversationTitle);
        }
        return mav;
    }

    @RequestMapping(value = "/conversation", method = RequestMethod.POST, params = "add")
    public ModelAndView addParticipant(@ModelAttribute ParticipantDTO participantDTO, BindingResult result, HttpServletRequest request){
        User user = userService.findByUserName(participantDTO.getUsername());
        Participant participant = participantService.
                findByUserAndConversation(user, conversation);
        if(user == null){
            result.reject("No such user");
        }
        if(participant != null){
            result.reject("Such user already is in conversation");
        }
        if(result.hasErrors()){
            return new ModelAndView("conversation");
        }
        Set<Role>  roles = new HashSet<>();
        roles.add(Role.USER);
        participantDTO.setRoles(roles);
        participantDTO.setUser(user);
        participantDTO.setConversation(conversation);
        participantService.save(participantDTO);
        return new ModelAndView("redirect:/conversation?title=" + conversation.getTitle());
    }

    @RequestMapping(value = "/conversation", method = RequestMethod.POST, params = "delete")
    public ModelAndView deleteParticipant(@ModelAttribute ParticipantDTO participantDTO, BindingResult result, HttpServletRequest request){
        User user = userService.findByUserName(participantDTO.getUsername());
        participantService.deleteByUserAndConversation(user, conversation);
        return new ModelAndView("redirect:/conversation?title=" + conversation.getTitle());
    }
}
