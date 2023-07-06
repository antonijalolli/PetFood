package com.example.seminarskaemt.Implementation;

import com.example.seminarskaemt.Entity.CustomUserPrincipal;
import com.example.seminarskaemt.Service.AuthService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImplementation implements AuthService {

    @Override
    public CustomUserPrincipal getCurrentUser() {
        var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof CustomUserPrincipal){
            return (CustomUserPrincipal)principal;
        }
        return null;
    }

    @Override
    public Long getCurrentUserId() {
        var currentUser = getCurrentUser();
        if(currentUser == null){
            return -1L;
        }

        return currentUser.getUserId();
    }
}
