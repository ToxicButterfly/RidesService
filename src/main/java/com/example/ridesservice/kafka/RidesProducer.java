package com.example.ridesservice.kafka;

import com.example.ridesservice.dto.request.DelegatePaymentRequest;
import com.example.ridesservice.model.Ride;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RidesProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(int id) {
        log.info("Looking for a driver");
        kafkaTemplate.send("driver-search", id);
    }

    public void delegatePayment(DelegatePaymentRequest request) {
        log.info("Sending request to Payment service to remit transaction");
        kafkaTemplate.send("payment-pay", request);
    }
}
