package com.springmvc.controller;

import com.springmvc.entity.Vehicle;
import com.springmvc.service.VehicleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String list(
            @RequestParam(required = false) String from,
            @RequestParam(required = false) String to,
            Model model
    ) {
        List<Vehicle> vehicles = vehicleService.findAll();

        List<Vehicle> availableVehicles = new ArrayList<>();

        if (from != null && !from.isEmpty() && to != null && !to.isEmpty()) {
            availableVehicles = vehicleService.findAvailableVehicles(
                    LocalDate.parse(from),
                    LocalDate.parse(to)
            );
        }

        model.addAttribute("vehicles", vehicles);
        model.addAttribute("availableVehicles", availableVehicles);

        return "vehicles";
    }

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public String getSaveUser(
            @RequestParam(required = false) Integer id,
            @RequestParam String action,
            Model model
    ) {
        Vehicle vehicle = new Vehicle();
        if (id != null) vehicle = vehicleService.findOneById(id);
        model.addAttribute("vehicle", vehicle);

        return "vehicles/" + action;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String postSaveUser(
            @ModelAttribute("vehicle") Vehicle vehicle
    ) {
        vehicleService.save(vehicle);

        return "redirect:/vehicles";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String postDeleteUser(
            @ModelAttribute("id") Integer id
    ) {
        vehicleService.delete(id);

        return "redirect:/vehicles";
    }
}
