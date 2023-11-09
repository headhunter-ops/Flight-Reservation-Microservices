package com.reservationmicroservice.feignClients;

import com.reservationmicroservice.payload.PassengerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PASSENGER-SERVICE", url = "http://localhost:8082") // Replace with the actual URL
public interface PassengerClient {

    @GetMapping("/passengers/{id}")
    PassengerDto getPassengerById(@PathVariable("id") Long id);
}
