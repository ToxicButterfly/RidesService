package com.example.ridesservice.kafka;

import com.example.ridesservice.dto.request.DelegatePaymentRequest;
import com.example.ridesservice.dto.request.DelegateRatingRequest;
import com.example.ridesservice.dto.request.DriverRequest;
import com.example.ridesservice.model.Ride;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RidesProducer {

    @Value("${topic.name.driver}")
    private String driverTopic;
    @Value("${topic.name.payment}")
    private String paymentTopic;
    @Value("${topic.name.rating}")
    private String ratingTopic;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(DriverRequest request) {
        log.info("Looking for a driver");
        kafkaTemplate.send(driverTopic, request);
    }

    public void delegatePayment(DelegatePaymentRequest request) {
        log.info("Sending request to Payment service to remit transaction");
        kafkaTemplate.send(paymentTopic, request);
    }

    public void delegateRating(DelegateRatingRequest request) {
        log.info("Sending request to Rating service to update rating");
        kafkaTemplate.send(ratingTopic, request);
    }
}
