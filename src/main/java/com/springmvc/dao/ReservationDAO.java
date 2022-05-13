package com.springmvc.dao;

import com.springmvc.entity.Reservation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReservationDAO implements DAO<Reservation> {

    private final SessionFactory sessionFactory;

    public ReservationDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Reservation> findAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Reservation> query = session.createQuery("FROM Reservation", Reservation.class);

            return query.getResultList();
        }
    }

    @Override
    public Reservation findOne(int id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Reservation> query = session.createQuery("FROM Reservation WHERE id = :id", Reservation.class);

            query.setParameter("id", id);

            return query.getSingleResult();
        }
    }

    @Override
    public Reservation save(Reservation reservation) {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            session.saveOrUpdate(reservation);

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

            Reservation reservation = findOne(id);

            if (reservation == null) throw new Exception("Reservation not found");

            session.delete(reservation);
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();

            e.printStackTrace();
        }
    }
}
