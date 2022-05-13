package com.springmvc.dao;

import com.springmvc.entity.Reservation;

import java.util.List;

public interface ReservationDAO {
    List<Reservation> findAll();

    Reservation findOneById(int id);

    Reservation save(Reservation reservation);

    void delete(int id);
}
