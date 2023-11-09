package com.passengermicroservice.controllers;


import com.passengermicroservice.exception.PassengerException;
import com.passengermicroservice.payload.PassengerDto;
import com.passengermicroservice.services.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/passengers")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    @PostMapping("/create")
    public ResponseEntity<PassengerDto> createPassenger(@Valid @RequestBody PassengerDto passengerDto) {
        PassengerDto createdPassenger = passengerService.createPassenger(passengerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPassenger);
    }

    @GetMapping("/")
    public ResponseEntity<List<PassengerDto>> getAllPassengers() {
        List<PassengerDto> passengers = passengerService.getAllPassengers();
        return ResponseEntity.ok(passengers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PassengerDto> getPassengerById(@PathVariable Long id) {
        Optional<PassengerDto> passengerDto = passengerService.getPassengerById(id);
        return passengerDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PassengerDto> updatePassenger(
            @PathVariable Long id,
            @Valid @RequestBody PassengerDto passengerDto
    ) {
        try {
            PassengerDto updatedPassenger = passengerService.updatePassenger(id, passengerDto);
            return ResponseEntity.ok(updatedPassenger);
        } catch (PassengerException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePassenger(@PathVariable Long id) {
        try {
            passengerService.deletePassenger(id);
            return ResponseEntity.ok("Passenger deleted successfully");
        } catch (PassengerException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
