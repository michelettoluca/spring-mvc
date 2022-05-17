package com.springmvc.dao;

import com.springmvc.entity.User;
import com.springmvc.type.UserRole;

import java.util.List;

public interface UserDAO {
    List<User> findAll();

    List<User> findManyByRole(UserRole role);

    User findOneById(int id);

    User findOneByUsername(String username);

    User save(User user);

    void delete(int id);
}
