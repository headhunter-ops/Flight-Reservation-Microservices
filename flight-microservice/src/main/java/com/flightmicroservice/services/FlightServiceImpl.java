package com.flightmicroservice.services;

import com.flightmicroservice.entities.*;
import com.flightmicroservice.exceptions.*;
import com.flightmicroservice.payload.*;
import com.flightmicroservice.repositories.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService{

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public FlightDto saveFlight(FlightDto flightDto) {
        Flight flight = mapToEntity(flightDto);
        Flight save = flightRepository.save(flight);
        FlightDto flightDto1 = mapToDto(save);
        return flightDto1;
    }

    @Override
    public Optional<FlightDto> getFlightById(Long id) {
        Flight flight = flightRepository.findById(id).orElseThrow(
                () -> new FlightReservationException("Flight not found with ID:" + id)
        );
        FlightDto flightDto = mapToDto(flight);
        return Optional.ofNullable(flightDto);
    }

    @Override
    public List<FlightDto> getAllFlights() {
        List<Flight> flights = flightRepository.findAll();
        List<FlightDto> dto = flights.stream().map(this::mapToDto).collect(Collectors.toList());
        return dto;

    }

    @Override
    public void deleteFlight(Long id) {
    flightRepository.findById(id).orElseThrow(
            ()-> new FlightReservationException("Flight not found with ID:"+id)
    );
    flightRepository.deleteById(id);
    }

    @Override
    public List<FlightDto> searchFlights(String arrivalCity, String departureCity, Date departureDate) {
        List<Flight> flights = flightRepository.findByArrivalCityAndDepartureCityAndDepartureDate(
                arrivalCity, departureCity, departureDate);

        // Convert the list of Flight entities to FlightDto objects
        return flights.stream().map(this::mapToDto).collect(Collectors.toList());
    }


    //----------//--------Conversion from dto to entity and vice versa------//---------//
    public FlightDto mapToDto(Flight flight){
        FlightDto dto = modelMapper.map(flight, FlightDto.class);
        return dto;
    }
    public Flight mapToEntity(FlightDto flightDto){
        Flight entity = modelMapper.map(flightDto, Flight.class);
        return entity;
    }
}
