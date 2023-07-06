package com.example.seminarskaemt.Service;

import com.example.seminarskaemt.Entity.User;
import com.example.seminarskaemt.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("userService")
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findById(Long id){
        return userRepository.findById(id).orElseThrow();
    }

    public User findByConfirmationToken(String confirmationToken) {
        return userRepository.findByConfirmationToken(confirmationToken);
    }

    public User findByUsernameAndPassword(String username, String password) {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (user.getFirstName().equalsIgnoreCase(username) &&
                    user.getPassword().equalsIgnoreCase(password)) {
                return user;
            }
        }
        return null;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

}
