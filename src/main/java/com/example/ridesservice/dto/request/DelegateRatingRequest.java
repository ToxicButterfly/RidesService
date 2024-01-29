package com.example.ridesservice.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DelegateRatingRequest {

    private Integer rideId;
    private Integer passId;
    private Integer driverId;
}

