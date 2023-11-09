package com.passengermicroservice.payload;

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

    @NotBlank(message = "Passenger's first name is required")
    @Size(max = 50, message = "First name cannot exceed 50 characters")
    private String firstName;

    @NotBlank(message = "Passenger's last name is required")
    @Size(max = 50, message = "Last name cannot exceed 50 characters")
    private String lastName;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    @Size(max = 100, message = "Email cannot exceed 100 characters")
    private String email;

    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    private String phoneNumber;

    @Past(message = "Date of birth must be in the past")
    @NotNull(message = "Date of birth is required")
    private LocalDate dateOfBirth;

    // Getters and setters
}