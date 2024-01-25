package com.example.ridesservice.controller;

import com.example.ridesservice.dto.request.PassengerRequestForRide;
import com.example.ridesservice.model.Location;
import com.example.ridesservice.service.RidesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/ride")
public class RidesController {

    @Autowired
    RidesService ridesService;

    @PostMapping("createTrip")
    public void createTrip(@RequestBody PassengerRequestForRide request) {
        ridesService.createTrip(request);
    }
}
