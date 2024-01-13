package com.example.ridesservice.service;

import com.example.ridesservice.dao.RidesDAO;
import com.example.ridesservice.feign.DriverMessagesInterface;
import com.example.ridesservice.model.Driver;
import com.example.ridesservice.model.Location;
import com.example.ridesservice.model.Ride;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static java.lang.Math.sqrt;

@Service
public class RidesService {

    @Autowired
    RidesDAO ridesDAO;
    @Autowired
    DriverMessagesInterface driverMessageInterface;
    @Autowired
    private SequenceGeneratorService service;
    public void createTrip(Location location, int passengerID) {
        long time = System.currentTimeMillis();
        double length = sqrt(Math.pow(location.getCoor2X()- location.getCoorX(), 2) + Math.pow(location.getCoor2Y()- location.getCoorY(),2));
        Driver driver = driverMessageInterface.findAvailableDriver().getBody();

        float cost = (float) Math.round((int) Math.ceil(driver.getRating()) * length * 100) /100;
        Ride ride = new Ride();
        ride.setId(service.getSequenceNumber(Ride.SEQUENCE_NAME));
        ride.setLength(length);
        ride.setTripDate(new Date());
        ride.setDriver_id(driver.getId());
        ride.setDriverRating(driver.getRating());
        ride.setPassenger_id(passengerID);
        ride.setPassengerRating(5.0F);
        ride.setTripDuration(System.currentTimeMillis()-time/1000);
        ride.setCost(cost);
        ridesDAO.save(ride);
        System.out.println(ride.toString());
    }
}
