package messenger.service;

import messenger.controllers.dto.UserRegistrationDTO;
import messenger.domain.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by flogiston on 03.06.19.
 */
public interface UserService extends UserDetailsService{
    User findByUserName(String userName);
    void save(UserRegistrationDTO userRegistrationDTO);
}
