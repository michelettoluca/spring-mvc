package com.springmvc.service.impl;

import com.springmvc.dao.ReservationDAO;
import com.springmvc.entity.Reservation;
import com.springmvc.service.ReservationService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {
    private final ReservationDAO dao;

    public ReservationServiceImpl(ReservationDAO dao) {
        this.dao = dao;
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
        return dao.save(reservation);
    }

    @Override
    public void delete(int id) {
        dao.delete(id);
    }
}
