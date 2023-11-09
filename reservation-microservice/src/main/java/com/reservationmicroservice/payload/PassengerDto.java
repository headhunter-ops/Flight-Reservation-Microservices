package com.reservationmicroservice.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassengerDto {

    private Long id;
       private String firstName;


    private String lastName;


    private String email;


    private String phoneNumber;


    private LocalDate dateOfBirth;

    // Getters and setters
}