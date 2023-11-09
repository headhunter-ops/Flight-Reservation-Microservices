package com.flightmicroservice.controllers;

import com.flightmicroservice.entities.*;
import com.flightmicroservice.exceptions.*;
import com.flightmicroservice.payload.*;
import com.flightmicroservice.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/flights")
public class FlightController {
   @Autowired
   private FlightService flightService;

    @GetMapping("/{id}")
    public ResponseEntity<FlightDto> getFlightById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(flightService.getFlightById(id).orElseThrow(() ->
                    new FlightReservationException("Flight not found with ID: " + id)));
        } catch (FlightReservationException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<FlightDto>> getAllFlights() {
        List<FlightDto> allFlights = flightService.getAllFlights();
        return ResponseEntity.ok(allFlights);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchFlights(
            @RequestParam String arrivalCity,
            @RequestParam String departureCity,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date departureDate) {

        List<FlightDto> flights = flightService.searchFlights(arrivalCity, departureCity, departureDate);

        if (flights.isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Flights not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        return ResponseEntity.ok(flights);
    }

    @PostMapping("/create")
    public ResponseEntity<FlightDto> createFlight(@Valid @RequestBody FlightDto flightDto) {
        FlightDto flightCreated = flightService.saveFlight(flightDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(flightCreated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFlight(@PathVariable Long id) {
        try {
            flightService.deleteFlight(id);
            return ResponseEntity.ok("Flight deleted successfully");
        } catch (FlightReservationException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
