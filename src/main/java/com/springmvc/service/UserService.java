package com.springmvc.service;

import com.springmvc.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findOneById(int id);

    User findOneByUsername(String username);

    void save(User user);

    void delete(int id);
}
