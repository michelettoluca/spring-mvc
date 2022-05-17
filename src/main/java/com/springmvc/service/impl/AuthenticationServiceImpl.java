package com.springmvc.service.impl;

import com.springmvc.service.UserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AuthenticationServiceImpl implements UserDetailsService {

    private final UserService userService;

    public AuthenticationServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.springmvc.entity.User user = userService.findOneByUsername(username);

        if (user == null) throw new UsernameNotFoundException("User not found");

        User.UserBuilder builder = User.withUsername(username);
//        builder.disabled((user.isEnabled()))
        builder.disabled(false);
        builder.password(user.getPassword());

        return builder.build();
    }
//
//    @Override
//    public User signIn(User user) {
//        User matchUser = userService.findOneByUsername(user.getUsername());
//
//        if (matchUser == null) return null;
//
//        if (matchUser.getPassword().equals(user.getPassword())) {
//
//            return user;
//        }
//        ;
//
//        return null;
//    }
//
//    @Override
//    public void signOut() {
//
//    }
}
