package com.springmvc.service;

import com.springmvc.entity.Vehicle;

import java.time.LocalDate;
import java.util.List;

public interface VehicleService {
    List<Vehicle> findAll();

    List<Vehicle> findAvailableVehicles(LocalDate from, LocalDate to);

    Vehicle findOneById(int id);

    Vehicle save(Vehicle vehicle);

    void delete(int id);

    Vehicle findOneByPlateNumber(String plateNumber);
}
