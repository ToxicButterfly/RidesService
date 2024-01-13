package com.example.ridesservice.model;

import lombok.Data;

import java.util.Date;
@Data
public class Driver {
    private Integer id;
    private String fullName;
    private String username;
    private String email;
    private Date registerDate;
    private Float rating;
}

