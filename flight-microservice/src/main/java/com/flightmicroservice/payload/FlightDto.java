package com.flightmicroservice.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightDto {

    private Long id;

    @NotBlank(message = "Flight number is required")
    @Size(min = 5, max = 10, message = "Flight number must be between 5 and 10 characters")
    private String flightNumber;

    @NotBlank(message = "Departure city is required")
    private String departureCity;

    @NotBlank(message = "Arrival city is required")
    private String arrivalCity;

    @FutureOrPresent(message = "Departure date must be in the future or present")
    private Date departureDate;

    @NotNull(message = "Arrival time is required")
    private LocalTime arrivalTime;

    @NotNull(message = "Departure time is required")
    private LocalTime departureTime;

    @Positive(message = "Ticket price must be a positive number")
    private double ticketPrice;

    @Min(value = 0, message = "Available seats must be at least 0")
    private int availableSeats;



    // Constructors, getters, and setters
}

