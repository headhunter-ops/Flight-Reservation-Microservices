package com.reservationmicroservice.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto {

    private Long id;

   // @NotBlank(message = "Passenger name is required")
    private String passengerName;

   // @NotNull(message = "Passenger ID is required")
    private Long passengerId;

   // @NotNull(message = "Flight ID is required")
    private Long flightId;

    ///@NotNull(message = "Reservation date is required")
    private LocalDate reservationDate;

   // @NotNull(message = "Number of tickets is required")
   // @Positive(message = "Number of tickets must be a positive number")
    private int numberOfTickets;

    //@Pattern(regexp = "^(Window|Aisle|Middle)$", message = "Invalid seat preference. Must be 'Window', 'Aisle', or 'Middle'")
    private String seatPreference;

    private boolean checkIn;

    private Long userId;

   }
