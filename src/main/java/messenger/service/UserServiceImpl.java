package messenger.service;

import messenger.controllers.dto.UserRegistrationDTO;
import messenger.data.UserRepository;
import messenger.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
    public User findByUserName(String userName) {
        return userRepository.getByUserName(userName);
    }

    @Override
    public void save(UserRegistrationDTO userRegistrationDTO) {
        User user = new User();
        user.setUserName(userRegistrationDTO.getUserName());
        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        System.out.println(user.toString());
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.getByUserName(s);
        Collection<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("USER"));
        return new org.springframework.security.core.userdetails.User(user.getUserName(),
                user.getPassword(), roles);
    }
}