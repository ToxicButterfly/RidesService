package com.example.ridesservice.service;

import com.example.ridesservice.dto.request.PassengerRequestForRide;
import com.example.ridesservice.dto.request.RideRequest;

public interface RidesService {
    void createTrip(PassengerRequestForRide request, String header);
    void provideTrip(RideRequest request);
}
