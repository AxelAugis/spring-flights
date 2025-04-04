package com.dev.travel.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.travel.model.Flight;
import com.dev.travel.repository.FlightRepository;

@Service
public class FlightService {
    
    @Autowired
    private FlightRepository flightRepository;

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight getFlightById(Long id) {
        return flightRepository.findById(id).orElse(null);
    }

    public Flight createFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public Flight updateFlight(String flightNumber, Flight flight) {
        if (flight != null) {
            flight.setFlightNumber(flightNumber);
            flight.setDepartureTime(flight.getDepartureTime());
            flight.setArrivalTime(flight.getArrivalTime());
            flight.setAirplane(flight.getAirplane());
            flight.setDepartureCity(flight.getDepartureCity());
            flight.setArrivalCity(flight.getArrivalCity());
            return flightRepository.save(flight);
        }
        return null;
    }

    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }

    public List<Flight> findFlightsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return flightRepository.findFlightsByDateRange(startDate, endDate);
    }

    public List<Flight> findFlightsByCity(Long cityId) {
        LocalDateTime todayStart = LocalDateTime.now().with(LocalTime.MIN);
        LocalDateTime tomorrowStart = todayStart.plusDays(1).with(LocalTime.MIN);
        return flightRepository.findByDepartureCityId(cityId, todayStart, tomorrowStart);
    }
}
