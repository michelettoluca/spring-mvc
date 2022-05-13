package com.springmvc.service.impl;

import com.springmvc.entity.User;
import com.springmvc.service.AuthenticationService;
import com.springmvc.service.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;

    public AuthenticationServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public User signIn(String username, String password) {
        User user = userService.findOneByUsername(username);

        if (user == null) return null;

        if (user.getPassword().equals(password)) return user;

        return null;
    }

    @Override
    public void signOut() {

    }
}
