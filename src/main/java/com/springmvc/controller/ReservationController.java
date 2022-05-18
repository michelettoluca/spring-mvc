package com.springmvc.controller;

import com.springmvc.entity.Reservation;
import com.springmvc.service.ReservationService;
import com.springmvc.type.ReservationStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getUserReservations(
            Model model
    ) {
        int tmpUserId = 15;

        List<Reservation> reservations = reservationService.findManyByUserId(tmpUserId);

        model.addAttribute("reservations", reservations);

        return "reservations";
    }

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public String getSaveReservation(
            Model model,
            @RequestParam Integer id
    ) {
        Reservation reservation = reservationService.findOneById(id);

        model.addAttribute("reservation", reservation);

        System.out.println(reservation.getUser());

        return "reservations/edit";
    }

    @RequestMapping(value = "/edit-status", method = RequestMethod.POST)
    public String postUpdateStatus(
            @ModelAttribute("id") Integer id,
            @ModelAttribute("origin") String origin,
            @ModelAttribute("status") ReservationStatus status
    ) {
        String redirectTo = "redirect:/";

        Reservation reservation = reservationService.findOneById(id);
        reservation.setStatus(status);
        reservationService.save(reservation);

        if (origin != null) return redirectTo + origin + "?userId=" + reservation.getUser().getId();

        return redirectTo + "reservations";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String postUpdateStatus(
            @ModelAttribute("reservation") Reservation reservation
    ) {
        System.out.println(reservation);
        reservationService.save(reservation);

        return "redirect:/reservations";
    }
}
