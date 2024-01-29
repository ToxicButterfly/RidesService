package com.example.ridesservice.repo;

import com.example.ridesservice.model.Ride;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RidesRepo extends MongoRepository<Ride, Integer> {
}
