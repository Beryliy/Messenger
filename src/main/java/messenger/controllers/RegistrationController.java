package messenger.controllers;

import messenger.controllers.dto.UserRegistrationDTO;
import messenger.domain.model.User;
import messenger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by flogiston on 03.06.19.
 */
@Controller
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    private UserService userService;

//    @ModelAttribute("user")
//    public UserRegistrationDTO userRegistrationDTO(){
//        return new UserRegistrationDTO();
//    }

    @GetMapping
    public ModelAndView showRegistrationForm(Model model){
        ModelAndView mav = new ModelAndView("registration");
        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO();
        mav.addObject("userRegistrationDTO", userRegistrationDTO);
        System.out.println("controller GET");
        return mav;
    }

    @PostMapping
    public ModelAndView registerUserAccount(@ModelAttribute @Valid UserRegistrationDTO userRegistrationDTO,
                               BindingResult result){
        System.out.println(userRegistrationDTO);
        User existingUser = userService.findByLogin(userRegistrationDTO.getLogin());
        if(existingUser != null){
            result.reject("Registration error.");
        }

        if(result.hasErrors()){
            return new ModelAndView("registration");
        }
        userService.save(userRegistrationDTO);
        return new ModelAndView("redirect:/registration?success");
    }
}