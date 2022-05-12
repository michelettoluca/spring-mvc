package com.springmvc.controller;

import com.springmvc.entity.Vehicle;
import com.springmvc.service.VehicleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/vehicles")
public class VehiclesController {

    @RequestMapping
    public String list(Model model) {
        List<Vehicle> vehicles = VehicleService.findAll();

        model.addAttribute("vehicles", vehicles);

        return "vehicles";
    }

    @RequestMapping(value = "/{plateNumber}")
    public String details(
            @PathVariable String plateNumber,
            Model model
    ) {
        Vehicle vehicle = VehicleService.findOneByPlateNumber(plateNumber);

        model.addAttribute("vehicle", vehicle);

        return "vehicle/details";
    }
}
