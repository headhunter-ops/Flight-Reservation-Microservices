package com.flightmicroservice.repositories;

import com.flightmicroservice.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findByArrivalCityAndDepartureCityAndDepartureDate(
            String arrivalCity,
            String departureCity,
            Date departureDate
    );


}
