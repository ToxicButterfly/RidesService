package com.example.ridesservice.feign;

import com.example.ridesservice.model.Driver;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("DRIVER-SERVICE")
public interface DriverMessagesInterface {
    @GetMapping("api/v1/driver/available")
    public ResponseEntity<Driver> findAvailableDriver();
}