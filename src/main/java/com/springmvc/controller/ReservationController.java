package com.springmvc.controller;

import com.springmvc.entity.Reservation;
import com.springmvc.entity.User;
import com.springmvc.entity.Vehicle;
import com.springmvc.service.ReservationService;
import com.springmvc.service.UserService;
import com.springmvc.service.VehicleService;
import com.springmvc.type.ReservationStatus;
import com.springmvc.utils.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequestMapping(value = "/reservations")
public class ReservationController {

    private final ReservationService reservationService;
    private final UserService userService;
    private final VehicleService vehicleService;

    public ReservationController(ReservationService reservationService, UserService userService, VehicleService vehicleService) {
        this.reservationService = reservationService;
        this.userService = userService;
        this.vehicleService = vehicleService;
    }

    //  ----
    //  GET requests
    //  ----

    @RequestMapping(method = RequestMethod.GET)
    public String getUserReservations(
            Model model
    ) {
        String username = Utils.getAuthenticatedUserUsername();

        User user = userService.findOneByUsername(username);

        model.addAttribute("reservations", user.getReservations());

        return "reservations";
    }

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public String getSaveReservation(
            Model model,
            @RequestParam String action,
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) Integer vehicleId,
            @RequestParam(required = false) String beginsAt,
            @RequestParam(required = false) String endsAt
    ) {
        Reservation reservation;
        if (action.equals("add")) {
            String username = Utils.getAuthenticatedUserUsername();
            User user = userService.findOneByUsername(username);
            Vehicle vehicle = vehicleService.findOneById(vehicleId);
            reservation = new Reservation(user, vehicle, LocalDate.parse(beginsAt), LocalDate.parse(endsAt), ReservationStatus.PENDING);
        } else {
            reservation = reservationService.findOneById(id);
        }

        model.addAttribute("reservation", reservation);

        return "reservations/" + action;
    }

    //  ----
    //  POST requests
    //  ----

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
    public String postSaveReservation(
            @ModelAttribute("reservation") Reservation reservation
    ) {
        reservationService.save(reservation);

        return "redirect:/reservations";
    }
}
