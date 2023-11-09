package com.reservationmicroservice.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserReservationDetailsDto {
    private UserDto user;
    private ReservationDto reservation;
    private PassengerDto passenger;
    private FlightDto flight;
}