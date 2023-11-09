package com.passengermicroservice.services;

import com.passengermicroservice.entities.Passenger;
import com.passengermicroservice.exception.PassengerException;
import com.passengermicroservice.payload.PassengerDto;
import com.passengermicroservice.repositories.PassengerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PassengerServiceImpl implements PassengerService{

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PassengerDto createPassenger(PassengerDto passengerDto) {
        Passenger passenger = mapToEntity(passengerDto);
        Passenger savedPassenger = passengerRepository.save(passenger);
        return mapToDto(savedPassenger);
    }

    @Override
    public List<PassengerDto> getAllPassengers() {
        List<Passenger> passengers = passengerRepository.findAll();
        return passengers.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PassengerDto> getPassengerById(Long id) {
        Passenger passenger = passengerRepository.findById(id).orElseThrow(
                () -> new PassengerException("Passenger not found with ID: " + id)
        );
        return Optional.of(mapToDto(passenger));
    }



    @Override
    public PassengerDto updatePassenger(Long id, PassengerDto passengerDto) {
        Passenger existingPassenger = passengerRepository.findById(id).orElseThrow(
                () -> new PassengerException("Passenger not found with ID:" + id)
        );

        // Update the existing passenger entity with the new data
        existingPassenger.setEmail(passengerDto.getEmail());
        existingPassenger.setDateOfBirth(passengerDto.getDateOfBirth());
        existingPassenger.setFirstName(passengerDto.getFirstName());
        existingPassenger.setLastName(passengerDto.getLastName());
        existingPassenger.setPhoneNumber(passengerDto.getPhoneNumber());

        // Save the updated passenger entity
        Passenger updatedPassenger = passengerRepository.save(existingPassenger);

        return mapToDto(updatedPassenger);
    }


    @Override
    public void deletePassenger(Long id) {
        passengerRepository.findById(id).orElseThrow(
                () -> new PassengerException("Passenger not found with ID:" + id)
        );
        passengerRepository.deleteById(id);
    }

    //-----------//--------------//--conversion from dto to entity and vice versa------------------//---------------------//-------------------//

    public Passenger mapToEntity(PassengerDto passengerDto){
        Passenger entity = modelMapper.map(passengerDto, Passenger.class);
        return entity;
    }

    public PassengerDto mapToDto(Passenger passenger){
        PassengerDto dto = modelMapper.map(passenger, PassengerDto.class);
        return dto;
    }
}
