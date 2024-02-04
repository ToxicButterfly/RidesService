package com.example.ridesservice.dto.request;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DelegatePaymentRequest {
    private Integer rideId;
    private Integer passId;
    private Integer driverId;
    private Float cost;

}
