package com.example.seminarskaemt.Implementation;

import com.example.seminarskaemt.Entity.CustomUserPrincipal;
import com.example.seminarskaemt.Entity.User;
import com.example.seminarskaemt.Repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        var users = userRepository.findAll();
        User user = null;
        for (var u: users) {
            if(u.getFirstName().equals(s)) {
                user = u;
            }
        }

        if (user == null) {
            throw new UsernameNotFoundException(s);
        }
        return new CustomUserPrincipal(user);
    }
}
