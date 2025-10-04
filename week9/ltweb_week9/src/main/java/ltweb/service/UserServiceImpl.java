package ltweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ltweb.entity.UserEntity;
import ltweb.repository.UserRepository;

@Service
public class UserServiceImpl implements UserDetailsService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.getUserByUsername(username);
        
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user with username: " + username);
        }
        
        return new MyUserService(user);
    }
}