package com.springmvc.dao;

import com.springmvc.config.HibernateConfig;
import com.springmvc.entity.Reservation;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReservationDAO implements DAO<Reservation> {

    public static List<Reservation> findAll() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Query<Reservation> query = session.createQuery("FROM Reservation", Reservation.class);

            return query.getResultList();
        }
    }

    public static Reservation findOne(int id) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Query<Reservation> query = session.createQuery("FROM Reservation WHERE id = :id", Reservation.class);

            query.setParameter("id", id);

            return query.getSingleResult();
        }
    }

    public static Reservation save(Reservation reservation) {
        Transaction transaction = null;

        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.saveOrUpdate(reservation);

            return reservation;
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

            Reservation reservation = findOne(id);

            if (reservation == null) throw new Exception("Reservation not found");

            session.delete(reservation);
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();

            e.printStackTrace();
        }
    }
}
