package com.example.ridesservice.service.impl;

import com.example.ridesservice.dto.request.*;
import com.example.ridesservice.kafka.RidesProducer;
import com.example.ridesservice.model.Ride;
import com.example.ridesservice.repo.RidesRepo;
import com.example.ridesservice.service.RidesService;
import com.example.ridesservice.service.SequenceGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

import static java.lang.Math.sqrt;

@Service
@RequiredArgsConstructor
public class RidesServiceImpl implements RidesService {

    final RidesRepo ridesRepo;
    private final SequenceGeneratorService service;
    private final RidesProducer ridesProducer;

    public void createTrip(PassengerRequestForRide request, String header) {

        long time = System.currentTimeMillis();
        double length = sqrt(Math.pow(request.getCoor2X()- request.getCoorX(), 2) + Math.pow(request.getCoor2Y()- request.getCoorY(),2));
        Ride ride = Ride.builder()
                .id(service.getSequenceNumber(Ride.SEQUENCE_NAME))
                .length(length)
                .tripDate(new Date())
                .passengerId(request.getPassId())
                .passengerRating(request.getPassRating())
                .tripDuration(System.currentTimeMillis()-time/1000)
                .build();
        Ride savedRide = ridesRepo.save(ride);
        ridesProducer.sendMessage(new DriverRequest(savedRide.getId(),header));
    }

    public void provideTrip(RideRequest request) {

        Ride ride = ridesRepo.findById(request.getRideId()).get();
        ride.setDriverId(request.getDriverId());
        ride.setDriverRating(request.getDriverRating());
        ridesRepo.save(remit(ride, request.getToken()));
        DelegateRatingRequest ratingRequest = DelegateRatingRequest.builder()
                .rideId(ride.getId())
                .driverId(ride.getDriverId())
                .passId(ride.getPassengerId())
                .token(request.getToken())
                .build();
        ridesProducer.delegateRating(ratingRequest);
    }

    private Ride remit(Ride ride, String token) {
        float cost = (float) Math.round((int) Math.ceil(ride.getDriverRating()) * ride.getLength() * 100)/100;
        ride.setCost(cost);
        DelegatePaymentRequest request = DelegatePaymentRequest.builder()
                .rideId(ride.getId())
                .driverId(ride.getDriverId())
                .passId(ride.getPassengerId())
                .cost(cost)
                .token(token)
                .build();
        ridesProducer.delegatePayment(request);
        return ride;
    }
}
