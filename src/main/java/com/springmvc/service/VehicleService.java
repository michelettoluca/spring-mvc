package com.springmvc.service;

import com.springmvc.dao.VehicleDAO;
import com.springmvc.entity.Vehicle;

import java.util.List;

public class VehicleService implements Service<Vehicle> {

    public static List<Vehicle> findAll() {
        return VehicleDAO.findAll();
    }

    public static Vehicle findOne(int id) {
        return VehicleDAO.findOne(id);
    }

    public static Vehicle findOneByPlateNumber(String plateNumber) {
        return VehicleDAO.findOneByPlateNumber(plateNumber);
    }

    public static void save(Vehicle user) {

    }

    public static void delete(int id) {

    }
}
