package com.example.ridesservice.dao;

import com.example.ridesservice.model.Ride;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RidesDAO extends MongoRepository<Ride, Integer> {
}
