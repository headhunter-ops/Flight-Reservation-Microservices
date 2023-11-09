package com.reservationmicroservice.repositories;

import com.reservationmicroservice.entities.Reservation;
import com.reservationmicroservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    List<Reservation> findByUser(User user);
}
