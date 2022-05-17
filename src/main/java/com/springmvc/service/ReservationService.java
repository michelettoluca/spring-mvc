package com.springmvc.service;

import com.springmvc.entity.Reservation;

import java.util.List;

public interface ReservationService {
    List<Reservation> findAll();

    List<Reservation> findManyByUserId(int userId);

    Reservation findOneById(int id);

    Reservation save(Reservation reservation);

    void delete(int id);
}
