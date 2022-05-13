package com.springmvc.dao;

import com.springmvc.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAO implements DAO<User> {

    //    @Autowired
//    private SessionFactory sessionFactory;
    private final SessionFactory sessionFactory;

    public UserDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> findAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("FROM User", User.class);

            return query.getResultList();
        }
    }

    @Override
    public User findOne(int id) {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("FROM User WHERE id = :id", User.class);

            query.setParameter("id", id);

            return query.getSingleResult();
        }
    }

    public User findOne(String username) {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("FROM User WHERE username = :username", User.class);
            query.setParameter("username", username);

            return query.getSingleResult();
        }
    }

    @Override
    public User save(User user) {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            session.saveOrUpdate(user);

            return user;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();

            e.printStackTrace();

            return null;
        }
    }

    @Override
    public void delete(int id) {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            User user = findOne(id);

            if (user == null) throw new Exception("User not found");

            session.delete(user);
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();

            e.printStackTrace();
        }
    }
}
