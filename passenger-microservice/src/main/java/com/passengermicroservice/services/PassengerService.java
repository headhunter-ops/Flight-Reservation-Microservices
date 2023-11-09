package com.passengermicroservice.services;


import com.passengermicroservice.payload.PassengerDto;

import java.util.List;
import java.util.Optional;

public interface PassengerService {

    PassengerDto createPassenger(PassengerDto passengerDto);

    List<PassengerDto> getAllPassengers();

    Optional<PassengerDto> getPassengerById(Long id);

    PassengerDto updatePassenger(Long id, PassengerDto passengerDto);

    void deletePassenger(Long id);
}
