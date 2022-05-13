package com.springmvc.service.impl;

import com.springmvc.dao.UserDAO;
import com.springmvc.entity.User;
import com.springmvc.service.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDAO dao;

    public UserServiceImpl(UserDAO dao) {
        this.dao = dao;
    }

    public List<User> findAll() {
        return dao.findAll();
    }

    public User findOne(int id) {
        return dao.findOne(id);
    }

    public void save(User user) {
        dao.save(user);
    }

    public void delete(int id) {
        dao.delete(id);
    }
}
