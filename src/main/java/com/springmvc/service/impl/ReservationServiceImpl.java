package com.springmvc.service.impl;

import com.springmvc.dao.ReservationDAO;
import com.springmvc.entity.Reservation;
import com.springmvc.entity.Vehicle;
import com.springmvc.service.ReservationService;
import com.springmvc.service.VehicleService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {
    private final ReservationDAO dao;
    private final VehicleService vehicleService;

    public ReservationServiceImpl(ReservationDAO dao, VehicleService vehicleService) {
        this.dao = dao;
        this.vehicleService = vehicleService;
    }

    @Override
    public List<Reservation> findAll() {
        return dao.findAll();
    }

    @Override
    public List<Reservation> findManyByUserId(int userId) {
        return dao.findManyByUserId(userId);
    }

    @Override
    public Reservation findOneById(int id) {
        return dao.findOneById(id);
    }

    @Override
    public Reservation save(Reservation reservation) {
        LocalDate beginsAt = reservation.getBeginsAt(),
                endsAt = reservation.getEndsAt();

        if (ChronoUnit.DAYS.between(beginsAt, endsAt) < 2) return null;

        Reservation tmpReservation = new Reservation(
                reservation.getUser(),
                reservation.getVehicle(),
                reservation.getBeginsAt(),
                reservation.getEndsAt(),
                reservation.getStatus()
        );

        dao.delete(reservation.getId());

        List<Vehicle> availableVehicles = vehicleService.findAvailableVehiclesCriteria(beginsAt, endsAt);

        if (availableVehicles
//                .contains(reservation.getVehicle()))
                .stream()
                .filter(vehicle -> vehicle.getId() == reservation.getVehicle().getId())
                .toArray()
                .length == 0)
            return null;

        return dao.save(tmpReservation);
    }

    @Override
    public void delete(int id) {
        dao.delete(id);
    }
}
