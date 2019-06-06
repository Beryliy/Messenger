package messenger.service;

import messenger.controllers.dto.UserRegistrationDTO;
import messenger.domain.model.User;

/**
 * Created by flogiston on 03.06.19.
 */
public interface UserService {
    User findByLogin(String login);
    void save(UserRegistrationDTO userRegistrationDTO);
}
