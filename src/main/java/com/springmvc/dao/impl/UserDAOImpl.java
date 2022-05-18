package com.springmvc.dao.impl;

import com.springmvc.dao.UserDAO;
import com.springmvc.entity.User;
import com.springmvc.type.UserRole;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    private final SessionFactory sessionFactory;

    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> findAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);

            Root<User> root = query.from(User.class);

            query.select(root);

            return session.createQuery(query).getResultList();
        }
    }

    @Override
    public List<User> findManyByRole(UserRole role) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);

            Root<User> root = query.from(User.class);

            Path<UserRole> rRole = root.get("role");

            query.select(root).where(criteriaBuilder.equal(rRole, role));

            return session.createQuery(query).getResultList();
        }
    }

    @Override
    public User findOneById(int id) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);

            Root<User> root = query.from(User.class);

            Path<Integer> rId = root.get("id");

            query.select(root).where(criteriaBuilder.equal(rId, id));

            return session.createQuery(query).getSingleResult();
        }
    }

    @Override
    public User findOneByUsername(String username) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);

            Root<User> root = query.from(User.class);

            Path<String> rUsername = root.get("username");

            query.select(root).where(criteriaBuilder.equal(rUsername, username));

            return session.createQuery(query).getSingleResult();
        }
    }

    @Override
    public User save(User user) {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            session.saveOrUpdate(user);

            transaction.commit();

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

            User user = findOneById(id);

            session.delete(session.merge(user));

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();

            e.printStackTrace();
        }
    }
}
