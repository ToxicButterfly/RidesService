package com.example.ridesservice.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Time;
import java.util.Date;

@Data
@Document(collection = "ride")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ride {
    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Id
    private Integer id;
    private Double length;
    private Long tripDuration;
    private Date tripDate;
    private Integer driverId;
    private Float driverRating;
    private Integer passengerId;
    private Float passengerRating;
    private Float cost;
}
