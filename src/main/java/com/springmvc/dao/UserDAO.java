package com.springmvc.dao;

import com.springmvc.entity.User;

import java.util.List;

public interface UserDAO {
    List<User> findAll();

    User findOneById(int id);

    User findOneByUsername(String username);

    User save(User user);

    void delete(int id);
}
