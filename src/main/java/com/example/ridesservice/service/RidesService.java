package com.example.ridesservice.service;

import com.example.ridesservice.dao.RidesDAO;
import com.example.ridesservice.dto.request.DelegatePaymentRequest;
import com.example.ridesservice.dto.request.PassengerRequestForRide;
import com.example.ridesservice.dto.request.RideRequest;
import com.example.ridesservice.feign.DriverMessagesInterface;
import com.example.ridesservice.kafka.RidesProducer;
import com.example.ridesservice.model.Ride;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static java.lang.Math.sqrt;

@Service
@RequiredArgsConstructor
public class RidesService {

    @Autowired
    RidesDAO ridesDAO;
    @Autowired
    DriverMessagesInterface driverMessageInterface;
    @Autowired
    private SequenceGeneratorService service;
    private final RidesProducer ridesProducer;

    public void createTrip(PassengerRequestForRide request) {
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
        Ride savedRide = ridesDAO.save(ride);
        ridesProducer.sendMessage(savedRide.getId());
    }

    public void provideTrip(RideRequest request) {
        Ride ride = ridesDAO.findById(request.getRideId()).get();
        ride.setDriverId(request.getDriverId());
        ride.setDriverRating(request.getDriverRating());
        Ride paidRide = remit(ride);
        ridesDAO.save(ride);
    }

    private Ride remit(Ride ride) {
        float cost = (float) Math.round((int) Math.ceil(ride.getDriverRating()) * ride.getLength() * 100)/100;
        ride.setCost(cost);
        DelegatePaymentRequest request = DelegatePaymentRequest.builder()
                .driverId(ride.getDriverId())
                .passId(ride.getPassengerId())
                .cost(cost)
                .build();
        ridesProducer.delegatePayment(request);
        return ride;
    }
}
