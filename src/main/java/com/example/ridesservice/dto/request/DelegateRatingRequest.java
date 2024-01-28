package com.example.ridesservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DelegateRatingRequest {

    private Integer rideId;
    private Integer passId;
    private Integer driverId;
}

