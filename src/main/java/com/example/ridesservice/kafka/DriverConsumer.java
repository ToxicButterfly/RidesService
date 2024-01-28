package com.example.ridesservice.kafka;


import com.example.ridesservice.dto.request.RideRequest;
import com.example.ridesservice.service.RidesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DriverConsumer {

    private final RidesService ridesService;

    @KafkaListener(topics = "driver-available", groupId = "rideGroup")
    public void receiveMessage(RideRequest request) {
        log.info("Received message: {}", request.toString());
        ridesService.provideTrip(request);
    }
}
