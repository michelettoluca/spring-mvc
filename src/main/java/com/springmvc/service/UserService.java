package com.springmvc.service;

import com.springmvc.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findOne(int id);

    void save(User entity);

    void delete(int id);
}
