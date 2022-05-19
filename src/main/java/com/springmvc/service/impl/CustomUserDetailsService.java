package com.springmvc.service.impl;

import com.springmvc.service.UserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("customUserDetailsService")
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.springmvc.entity.User user = userService.findOneByUsername(username);

        if (user == null) throw new UsernameNotFoundException("User not found");

        String[] roles = new String[]{"ROLE_" + user.getRole()};

        User.UserBuilder userBuilder = User
                .withUsername(user.getUsername())
                .password(user.getPassword())
//                .disabled(user.isActive())
                .disabled(false)
                .authorities(roles);

        return userBuilder.build();
    }
}