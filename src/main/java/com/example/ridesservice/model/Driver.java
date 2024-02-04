package com.example.ridesservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Driver {
    private Integer id;
    private String fullName;
    private String username;
    private String email;
    private Date registerDate;
    private Float rating;

}

