package com.springmvc.service;

import com.springmvc.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthenticationService extends UserDetailsService {
    User signIn(User user);

    void signOut();
}
