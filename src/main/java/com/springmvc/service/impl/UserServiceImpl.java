package com.springmvc.service.impl;

import com.springmvc.dao.UserDAO;
import com.springmvc.entity.User;
import com.springmvc.service.UserService;
import com.springmvc.type.UserRole;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDAO dao;

    @Qualifier("customPasswordEncoder")
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(
            UserDAO dao,
            PasswordEncoder passwordEncoder
    ) {
        this.dao = dao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> findAll() {
        return dao.findAll();
    }

    @Override
    public List<User> findManyByRole(UserRole role) {
        return dao.findManyByRole(role);
    }

    @Override
    public User findOneById(int id) {
        return dao.findOneById(id);
    }

    @Override
    public User findOneByUsername(String username) {
        return dao.findOneByUsername(username);
    }

    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return dao.save(user);
    }

    @Override
    public void delete(int id) {
        dao.delete(id);
    }
}
