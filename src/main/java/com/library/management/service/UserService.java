package com.library.management.service;

import com.library.management.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    public void registerUser(User user);
    public User findUserByEmail(String email);
}
