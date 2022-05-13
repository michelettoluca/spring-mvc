package com.springmvc.service.impl;

import com.springmvc.dao.impl.UserDAOImpl;
import com.springmvc.entity.User;
import com.springmvc.service.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDAOImpl dao;

    public UserServiceImpl(UserDAOImpl dao) {
        this.dao = dao;
    }

    @Override
    public List<User> findAll() {
        return dao.findAll();
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
    public void save(User user) {
        dao.save(user);
    }

    @Override
    public void delete(int id) {
        dao.delete(id);
    }
}
