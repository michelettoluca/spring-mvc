package com.springmvc.dao.impl;

import com.springmvc.dao.VehicleDAO;
import com.springmvc.entity.Vehicle;
import com.springmvc.type.ReservationStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;

@Repository
public class VehicleDAOImpl implements VehicleDAO {

    private final SessionFactory sessionFactory;

    public VehicleDAOImpl(SessionFactory sessionFactory) {
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
    public List<Vehicle> findAvailableVehicles(LocalDate from, LocalDate to) {
        try (Session session = sessionFactory.openSession()) {
            Query<Vehicle> query = session.createQuery(
                    "from Vehicle as v where v.id not in " +
                            "(select v2.id from Vehicle as v2 inner join Reservation as r on r.vehicle.id = v2.id " +
                            "where r.beginsAt < :from and r.endsAt > :to and (r.status != :status))",
                    Vehicle.class);

            query.setParameter("from", from);
            query.setParameter("to", to);
            query.setParameter("status", ReservationStatus.DENIED);

            return query.getResultList();
        }
    }

    @Override
    public Vehicle findOneById(int id) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Vehicle> query = criteriaBuilder.createQuery(Vehicle.class);

            Root<Vehicle> root = query.from(Vehicle.class);

            Path<Integer> rId = root.get("id");

            query.select(root).where(criteriaBuilder.equal(rId, id));

            return session.createQuery(query).getSingleResult();
        }
    }

    @Override
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

            transaction.commit();

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

            Vehicle vehicle = findOneById(id);

//            if (vehicle == null) throw new Exception("Vehicle not found");

            session.delete(vehicle);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();

            e.printStackTrace();
        }
    }
}
