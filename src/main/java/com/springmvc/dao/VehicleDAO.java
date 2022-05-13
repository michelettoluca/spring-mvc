package com.springmvc.dao;

import com.springmvc.entity.Vehicle;

import java.util.List;

public interface VehicleDAO {
    List<Vehicle> findAll();

    Vehicle findOneById(int id);

    Vehicle findOneByPlateNumber(String id);

    Vehicle save(Vehicle vehicle);

    void delete(int id);
}
