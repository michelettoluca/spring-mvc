package com.springmvc.dao.impl;

import com.springmvc.dao.ReservationDAO;
import com.springmvc.entity.Reservation;
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
public class ReservationDAOImpl implements ReservationDAO {

    private final SessionFactory sessionFactory;

    public ReservationDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Reservation> findAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Reservation> query = criteriaBuilder.createQuery(Reservation.class);

            Root<Reservation> root = query.from(Reservation.class);

            query.select(root);

            return session.createQuery(query).getResultList();
        }
    }

    @Override
    public List<Reservation> findManyByUserId(int userId) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Reservation> query = criteriaBuilder.createQuery(Reservation.class);

            Root<Reservation> root = query.from(Reservation.class);

            Path<Integer> rUserId = root.get("user").get("id");

            query.select(root).where(criteriaBuilder.equal(rUserId, userId));

            return session.createQuery(query).getResultList();
        }
    }

    @Override
    public Reservation findOneById(int id) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Reservation> query = criteriaBuilder.createQuery(Reservation.class);

            Root<Reservation> root = query.from(Reservation.class);

            Path<Integer> rId = root.get("id");

            query.select(root).where(criteriaBuilder.equal(rId, id));

            return session.createQuery(query).getSingleResult();
        }
    }

    @Override
    public Reservation save(Reservation reservation) {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            session.saveOrUpdate(reservation);

            transaction.commit();

            return reservation;
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

            Reservation reservation = findOneById(id);

//            if (reservation == null) throw new Exception("Reservation not found");

            session.delete(reservation);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();

            e.printStackTrace();
        }
    }
}
