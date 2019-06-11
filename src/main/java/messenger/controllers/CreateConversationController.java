package messenger.controllers;

import messenger.controllers.dto.ConversationDTO;
import messenger.controllers.dto.ParticipantDTO;
import messenger.domain.model.Conversation;
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

/**
 * Created by flogiston on 10.06.19.
 */
@Controller
public class CreateConversationController {
    private Conversation conversation;
    @Autowired
    private UserService userService;
    @Autowired
    private ConversationService conversationService;
    @Autowired
    private ParticipantService participantService;
    @RequestMapping(value = "/createConversation", method = RequestMethod.GET)
    public ModelAndView showCreateForm(){
        ModelAndView mav = new ModelAndView("createConversation");
        ConversationDTO conversationDTO = new ConversationDTO();
        mav.addObject("conversationDTO", conversationDTO);
        ParticipantDTO participantDTO = new ParticipantDTO();
        mav.addObject("participantDTO", participantDTO);
        return mav;
    }

    @RequestMapping(value = "/createConversation", method = RequestMethod.POST)
    public ModelAndView saveConversation(@ModelAttribute ConversationDTO conversationDTO,
                                         BindingResult result){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User currentUser = userService.findByUserName(userName);

        conversation = conversationService.findByTitle(conversationDTO.getTitle());
        if(conversation != null){
            result.reject("Conversation with the same title already exists");
        }
        if(result.hasErrors()){
            return new ModelAndView("createConversation");
        }
        conversationDTO.setCreator(currentUser);
        conversation = conversationService.save(conversationDTO);
        return new ModelAndView("redirect:/conversation?title="+conversationDTO.getTitle());
    }

//    @RequestMapping(value = "/createConversation", method = RequestMethod.GET)
//    public ModelAndView showAddForm(){
//        ModelAndView mav = new ModelAndView();
//        ParticipantDTO participantDTO = new ParticipantDTO();
//        mav.addObject("participantDTO", participantDTO);
//        return mav;
//    }

//    @RequestMapping(value = "/createConversation", method = RequestMethod.POST)
//    public void addParticipant(@ModelAttribute ParticipantDTO participantDTO, BindingResult result){
//        if(conversation == null){
//            result.reject("create a conversation first");
//        }
//        User member = userService.findByUserName(participantDTO.getUsername());
//        if(member == null){
//            result.reject("no such user");
//        }
//        Participant participant = participantService.findByUserAndConversation(member, conversation);
//        if(participant != null){
//            result.reject("Such user already is in conversation");
//        }
//        if(result.hasErrors()){
//            //return new ModelAndView("addParticipant");
//        }
//        Set<Role> roles = new HashSet<>();
//        roles.add(Role.USER);
//        participantDTO.setConversation(conversation);
//        participantDTO.setUser(member);
//        participantDTO.setRoles(roles);
//        if(participants == null){
//            participants = new ArrayList<>();
//        }
//        participants.add(participantDTO);
//        //return new ModelAndView("addParticipants");
//    }
}
