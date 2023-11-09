package com.flightmicroservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name="flights")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Flight number is required")
    @Size(min = 5, max = 10, message = "Flight number must be between 5 and 10 characters")
    private String flightNumber;

    @NotBlank(message = "Departure city is required")
    private String departureCity;

    @NotBlank(message = "Arrival city is required")
    private String arrivalCity;

    @FutureOrPresent(message = "Departure date must be in the future or present")
    @Temporal(TemporalType.DATE)
    private Date departureDate;

    @NotNull(message = "Arrival time is required")
    private LocalTime arrivalTime;

    @NotNull(message = "Departure time is required")
    private LocalTime departureTime;

    @Positive(message = "Ticket price must be a positive number")
    private double ticketPrice;

    @Min(value = 0, message = "Available seats must be at least 0")
    private int availableSeats;


    // @NotBlank: Ensures that the annotated field is not null and not just whitespace.
   // @Size: Specifies the minimum and maximum length of a field.
   // @FutureOrPresent: Ensures that the departureDate is in the future or the present.
   // @Positive: Requires that the ticketPrice is a positive number.
    // @Min: Specifies the minimum value for availableSeats.

}