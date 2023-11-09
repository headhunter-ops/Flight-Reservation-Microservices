package com.reservationmicroservice.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    @NotBlank(message = "Username is required")
    @Size(min = 5, max = 20, message = "Username must be between 5 and 20 characters")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    private Date dateOfBirth;
}
