package com.example.seminarskaemt.Service;

import com.example.seminarskaemt.Entity.CustomUserPrincipal;
import com.example.seminarskaemt.Entity.User;

public interface AuthService {
    CustomUserPrincipal getCurrentUser();
    Long getCurrentUserId();
}