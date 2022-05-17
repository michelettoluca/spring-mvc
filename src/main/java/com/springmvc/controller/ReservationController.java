package com.springmvc.controller;

import com.springmvc.entity.Reservation;
import com.springmvc.service.ReservationService;
import com.springmvc.type.ReservationStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
}
