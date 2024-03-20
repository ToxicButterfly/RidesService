package com.example.ridesservice.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideRequest {
    private Integer rideId;
    private Integer driverId;
    private Float driverRating;
    private String token;
}
