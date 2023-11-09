package com.reservationmicroservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="reservations")
public class Reservation {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

       // @NotBlank(message = "Passenger name is required")
        private String passengerName;

        //@NotNull(message = "Passenger ID is required")
        private Long passengerId;

       // @NotNull(message = "Flight ID is required")
        private Long flightId;

       // @NotNull(message = "Reservation date is required")
        private LocalDate reservationDate;

       // @NotNull(message = "Number of tickets is required")
       // @Positive(message = "Number of tickets must be a positive number")
        private int numberOfTickets;

        private boolean checkIn;  // Represent check-in as a boolean

        //@Pattern(regexp = "^(Window|Aisle|Middle)$", message = "Invalid seat preference. Must be 'Window', 'Aisle', or 'Middle'")
        private String seatPreference;

        @ManyToOne
        @JoinColumn(name = "user_id")
        private User user;

}
