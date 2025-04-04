package com.dev.travel.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dev.travel.model.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {    
    @Query("SELECT f FROM Flight f WHERE f.departureTime >= :startDate AND f.departureTime <= :endDate")
    List<Flight> findFlightsByDateRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT f FROM Flight f WHERE f.departureCity.id = :cityId AND f.departureTime >= :todayStart AND f.departureTime < :tomorrowStart")
    List<Flight> findByDepartureCityId(
        @Param("cityId") Long cityId, 
        @Param("todayStart") LocalDateTime todayStart,
        @Param("tomorrowStart") LocalDateTime tomorrowStart
    );
}
