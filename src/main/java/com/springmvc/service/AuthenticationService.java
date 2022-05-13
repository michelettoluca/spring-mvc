package com.springmvc.service;

import com.springmvc.entity.User;

public interface AuthenticationService {
    User signIn(String username, String password);

    void signOut();
}
