package com.example.ridesservice.util;

import com.example.ridesservice.dto.request.DriverRequest;
import com.example.ridesservice.dto.request.PassengerRequestForRide;
import com.example.ridesservice.dto.request.RideRequest;
import com.example.ridesservice.model.Ride;
import lombok.experimental.UtilityClass;

import java.util.Date;

@UtilityClass
public class TestUtils {

    public final Integer DEFAULT_ID = 1;
    public final Double DEFAULT_LENGTH = 1D;
    public final Long DEFAULT_TRIP_DURATION = 1L;
    public final Date DEFAULT_TRIP_DATE = new Date();
    public final Float DEFAULT_RATING = 5.0F;
    public final Float DEFAULT_COST = 1f;
    public final Integer DEFAULT_COORDINATE = 1;
    public final String DEFAULT_SEQUENCE_NAME = "users_sequence";
    public PassengerRequestForRide getDefaultPassengerRequestForRide() {
        return PassengerRequestForRide.builder()
                .passId(DEFAULT_ID)
                .coorX(DEFAULT_COORDINATE)
                .coorY(DEFAULT_COORDINATE)
                .coor2X(DEFAULT_COORDINATE)
                .coor2Y(DEFAULT_COORDINATE)
                .passRating(DEFAULT_RATING)
                .build();
    }

    public DriverRequest getDefaultDriverRequest() {
        return DriverRequest.builder()
                .id(DEFAULT_ID)
                .build();
    }

    public Ride getDefaultRide() {
        return Ride.builder()
                .id(DEFAULT_ID)
                .length(DEFAULT_LENGTH)
                .tripDuration(DEFAULT_TRIP_DURATION)
                .tripDate(DEFAULT_TRIP_DATE)
                .driverId(DEFAULT_ID)
                .driverRating(DEFAULT_RATING)
                .passengerId(DEFAULT_ID)
                .passengerRating(DEFAULT_RATING)
                .cost(DEFAULT_COST)
                .build();
    }

    public RideRequest getDefaultRideRequest() {
        return RideRequest.builder()
                .rideId(DEFAULT_ID)
                .driverId(DEFAULT_ID)
                .driverRating(DEFAULT_RATING)
                .build();
    }
}
