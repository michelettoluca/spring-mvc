package com.springmvc.dao;

import com.springmvc.entity.Vehicle;

import java.time.LocalDate;
import java.util.List;

public interface VehicleDAO {
    List<Vehicle> findAll();

    List<Vehicle> findAvailableVehicles(LocalDate from, LocalDate to);

    Vehicle findOneById(int id);

    Vehicle findOneByPlateNumber(String id);

    Vehicle save(Vehicle vehicle);

    void delete(int id);

    List<Vehicle> findAvailableVehiclesCriteria(LocalDate from, LocalDate to);
}
