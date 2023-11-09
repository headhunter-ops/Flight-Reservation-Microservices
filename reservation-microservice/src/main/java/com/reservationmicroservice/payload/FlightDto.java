package com.reservationmicroservice.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightDto {

    private Long id;


    private String flightNumber;


    private String departureCity;


    private String arrivalCity;


    private Date departureDate;


    private LocalTime arrivalTime;


    private LocalTime departureTime;


    private double ticketPrice;


    private int availableSeats;



    // Constructors, getters, and setters
}

