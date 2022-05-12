package com.springmvc.dao;

import com.springmvc.config.HibernateConfig;
import com.springmvc.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAO implements DAO<User> {

    public static List<User> findAll() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("SELECT User FROM User", User.class);

            return query.getResultList();
        }
    }

    public static User findOne(int id) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("SELECT User FROM User WHERE id = :id", User.class);

            query.setParameter("id", id);

            return query.getSingleResult();
        }
    }

    public static User findOne(String username) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("SELECT User FROM User WHERE username = :username", User.class);
            query.setParameter("username", username);

            return query.getSingleResult();
        }
    }

    public static User save(User user) {
        Transaction transaction = null;

        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.saveOrUpdate(user);

            return user;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();

            e.printStackTrace();

            return null;
        }
    }

    public static void delete(int id) {
        Transaction transaction = null;

        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
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
