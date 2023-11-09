package com.passengermicroservice.repositories;

import com.passengermicroservice.entities.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
}
