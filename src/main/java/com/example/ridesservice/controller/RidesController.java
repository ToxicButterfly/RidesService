package com.example.ridesservice.controller;

import com.example.ridesservice.dto.request.PassengerRequestForRide;
import com.example.ridesservice.service.RidesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/rides")
public class RidesController {

    final RidesService ridesService;

    @PostMapping("createTrip")
    public void createTrip(@RequestBody PassengerRequestForRide request, @RequestHeader(value = "Authorization") String header) {
        ridesService.createTrip(request, header);
    }
}
