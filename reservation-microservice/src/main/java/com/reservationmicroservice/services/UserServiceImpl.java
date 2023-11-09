package com.reservationmicroservice.services;

import com.reservationmicroservice.entities.Reservation;
import com.reservationmicroservice.entities.User;
import com.reservationmicroservice.exception.ReservationException;
import com.reservationmicroservice.feignClients.FlightClient;
import com.reservationmicroservice.feignClients.PassengerClient;
import com.reservationmicroservice.payload.*;
import com.reservationmicroservice.repositories.ReservationRepository;
import com.reservationmicroservice.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FlightClient flightClient;

    @Autowired
    private PassengerClient passengerClient;

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ReservationException("User not found with ID:" + id)
        );
        return mapToDto(user);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = mapToEntity(userDto);
        User savedUser = userRepository.save(user);
        return mapToDto(savedUser);
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ReservationException("User not found with ID:" + id)
        );
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
       user.setDateOfBirth(userDto.getDateOfBirth());
        User savedUser = userRepository.save(user);
        return mapToDto(savedUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.findById(id).orElseThrow(
                () -> new ReservationException("User not found with ID:" + id)
        );
        userRepository.deleteById(id);
    }

    @Override
    public ReservationDto createReservation(Long userId, ReservationDto reservationDto) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        // Check if the provided package and destination exist
        FlightDto flightById = flightClient.getFlightById(reservationDto.getFlightId());

        PassengerDto passengerById = passengerClient.getPassengerById(reservationDto.getPassengerId());

        if (flightById == null || passengerById == null) {
            throw new IllegalArgumentException("Invalid Flight or Passenger");
        }

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setFlightId(reservationDto.getFlightId());
        reservation.setPassengerId(reservationDto.getPassengerId());
        reservation.setReservationDate(reservationDto.getReservationDate());
        reservation.setPassengerName(reservationDto.getPassengerName());
        reservation.setNumberOfTickets(reservationDto.getNumberOfTickets());
        reservation.setSeatPreference(reservationDto.getSeatPreference());
        reservation.setCheckIn(true);
        Reservation save1 = reservationRepository.save(reservation);
        return mapToDto(save1);
    }

    @Override
    public List<ReservationDto> getUserReservation(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ReservationException("User not found with ID:" + userId)
        );

        List<Reservation> reservations = reservationRepository.findByUser(user);
        return reservations.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserReservationDetailsDto> getUserReservationDetails(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ReservationException("User not found with ID:" + userId)
        );

        List<Reservation> reservations = reservationRepository.findByUser(user);

        return reservations.stream()
                .map(reservation -> {
                    UserReservationDetailsDto dto = new UserReservationDetailsDto();
                    dto.setUser(mapToDto(user));
                    dto.setReservation(mapToDto(reservation));
                    dto.setFlight(flightClient.getFlightById(reservation.getFlightId()));
                    dto.setPassenger(passengerClient.getPassengerById(reservation.getPassengerId()));
                    return dto;
                })
                .collect(Collectors.toList());
    }

    //-----------------//---conversion from dto to entity and vice versa---------//---------------------//
    public User mapToEntity(UserDto userDto){
        return modelMapper.map(userDto,User.class);
    }

    public UserDto mapToDto(User user){
        return modelMapper.map(user,UserDto.class);
    }

    public Reservation mapToEntity(ReservationDto reservationDto){
        return modelMapper.map(reservationDto,Reservation.class);
    }
    public ReservationDto mapToDto(Reservation reservation){
        return modelMapper.map(reservation,ReservationDto.class);
    }

    private UserReservationDetailsDto mapToUserBookingDetailsDto(Reservation reservation, User user) {
        UserReservationDetailsDto dto = new UserReservationDetailsDto();
        dto.setUser(mapToDto(user));
        dto.setReservation(mapToDto(reservation));
        return dto;
    }
}