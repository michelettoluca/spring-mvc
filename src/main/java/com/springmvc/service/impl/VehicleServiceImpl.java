package com.springmvc.service.impl;

import com.springmvc.dao.VehicleDAO;
import com.springmvc.entity.Vehicle;
import com.springmvc.service.VehicleService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public Vehicle findOne(int id) {
        return dao.findOne(id);
    }

    @Override
    public Vehicle findOneByPlateNumber(String plateNumber) {
        return dao.findOneByPlateNumber(plateNumber);
    }

    @Override
    public void save(Vehicle user) {
    }

    @Override
    public void delete(int id) {
    }
}
