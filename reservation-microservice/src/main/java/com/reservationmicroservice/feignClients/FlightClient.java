package com.reservationmicroservice.feignClients;
import com.reservationmicroservice.payload.FlightDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "FLIGHT-SERVICE", url = "http://localhost:8081") // Replace with the actual URL
public interface FlightClient {

    @GetMapping("/flights/{id}")
    FlightDto getFlightById(@PathVariable("id") Long id);
}

