package com.springmvc.service;

import com.springmvc.entity.Vehicle;

import java.util.List;

public interface VehicleService {
    List<Vehicle> findAll();

    Vehicle findOne(int id);

    void save(Vehicle entity);

    void delete(int id);

    Vehicle findOneByPlateNumber(String plateNumber);
}
