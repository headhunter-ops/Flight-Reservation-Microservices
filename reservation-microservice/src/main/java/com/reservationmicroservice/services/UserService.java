package com.reservationmicroservice.services;

import com.reservationmicroservice.payload.ReservationDto;
import com.reservationmicroservice.payload.UserDto;
import com.reservationmicroservice.payload.UserReservationDetailsDto;

import java.util.List;

public interface UserService {

        List<UserDto> getAllUsers();
        UserDto getUserById(Long id);
        UserDto createUser(UserDto userDto);
        UserDto updateUser(Long id, UserDto userDto);
        void deleteUser(Long id);
        ReservationDto createReservation(Long userId,ReservationDto reservationDto);
        List<ReservationDto> getUserReservation(Long userId);
        List<UserReservationDetailsDto> getUserReservationDetails(Long userId);
    }