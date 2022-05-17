package com.springmvc.service.impl;

import com.springmvc.dao.VehicleDAO;
import com.springmvc.entity.Vehicle;
import com.springmvc.service.VehicleService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {
    private final VehicleDAO dao;

    public VehicleServiceImpl(VehicleDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<Vehicle> findAll() {
        return dao.findAll();
    }

    @Override
    public List<Vehicle> findAvailableVehicles(LocalDate from, LocalDate to) {
        if (from.isAfter(to)) return new ArrayList<>();

        return dao.findAvailableVehicles(from, to);
    }

    @Override
    public Vehicle findOneById(int id) {
        return dao.findOneById(id);
    }

    @Override
    public Vehicle findOneByPlateNumber(String plateNumber) {
        return dao.findOneByPlateNumber(plateNumber);
    }

    @Override
    public Vehicle save(Vehicle vehicle) {
        return dao.save(vehicle);
    }

    @Override
    public void delete(int id) {
        dao.delete(id);
    }
}
