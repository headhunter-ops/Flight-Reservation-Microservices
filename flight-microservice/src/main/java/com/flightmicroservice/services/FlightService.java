package com.flightmicroservice.services;

import com.flightmicroservice.payload.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface FlightService {


        FlightDto saveFlight(FlightDto flightDto);

        Optional<FlightDto> getFlightById(Long id);

        List<FlightDto> getAllFlights();

        void deleteFlight(Long id);

        List<FlightDto> searchFlights(String arrivalCity, String departureCity, Date departureDate);


}
