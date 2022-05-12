package com.springmvc.dao;

import com.springmvc.config.HibernateConfig;
import com.springmvc.entity.Vehicle;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class VehicleDAO implements DAO<Vehicle> {

    public static List<Vehicle> findAll() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Query<Vehicle> query = session.createQuery("FROM Vehicle", Vehicle.class);

            return query.list();
        }
    }

    public static Vehicle findOne(int id) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Query<Vehicle> query = session.createQuery("FROM Vehicle WHERE id = :id", Vehicle.class);

            query.setParameter("id", id);

            return query.getSingleResult();
        }
    }

    public static Vehicle findOneByPlateNumber(String plateNumber) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Query<Vehicle> query = session.createQuery("FROM Vehicle WHERE plateNumber = :plateNumber", Vehicle.class);

            query.setParameter("plateNumber", plateNumber);

            return query.getSingleResult();
        }
    }

    public static Vehicle save(Vehicle vehicle) {
        Transaction transaction = null;

        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.saveOrUpdate(vehicle);

            return vehicle;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();

            e.printStackTrace();

            return null;
        }
    }

    public static void delete(int id) throws Exception {
        Transaction transaction = null;

        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Vehicle vehicle = findOne(id);

            if (vehicle == null) throw new Exception("User not found");

            session.delete(vehicle);
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();

            e.printStackTrace();
        }
    }
}
