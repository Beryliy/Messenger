package messenger.service;

import messenger.controllers.dto.UserRegistrationDTO;
import messenger.data.UserRepository;
import messenger.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by flogiston on 03.06.19.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public User findByLogin(String login) {
        return userRepository.getByLogin(login);
    }

    @Override
    public void save(UserRegistrationDTO userRegistrationDTO) {
        User user = new User();
        user.setName(userRegistrationDTO.getName());
        user.setLogin(userRegistrationDTO.getLogin());
        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        System.out.println(user.toString());
        userRepository.save(user);
    }
}
