package com.springmvc.dao;

import com.springmvc.entity.Vehicle;
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
public class VehicleDAO implements DAO<Vehicle> {

    private final SessionFactory sessionFactory;

    public VehicleDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Vehicle> findAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Vehicle> query = criteriaBuilder.createQuery(Vehicle.class);

            Root<Vehicle> root = query.from(Vehicle.class);

            query.select(root);

            return session.createQuery(query).getResultList();
        }
    }

    @Override
    public Vehicle findOne(int id) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Vehicle> query = criteriaBuilder.createQuery(Vehicle.class);

            Root<Vehicle> root = query.from(Vehicle.class);

            Path<Integer> rId = root.get("id");

            query.select(root).where(criteriaBuilder.equal(rId, id));

            return session.createQuery(query).getSingleResult();
        }
    }

    public Vehicle findOneByPlateNumber(String plateNumber) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Vehicle> query = criteriaBuilder.createQuery(Vehicle.class);

            Root<Vehicle> root = query.from(Vehicle.class);

            Path<String> rPlateNumber = root.get("plateNumber");

            query.select(root).where(criteriaBuilder.equal(rPlateNumber, plateNumber));

            return session.createQuery(query).getSingleResult();
        }
    }

    @Override
    public Vehicle save(Vehicle vehicle) {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            session.saveOrUpdate(vehicle);

            return vehicle;
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

            Vehicle vehicle = findOne(id);

//            if (vehicle == null) throw new Exception("Vehicle not found");

            session.delete(vehicle);
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();

            e.printStackTrace();
        }
    }
}
