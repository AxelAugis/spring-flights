package com.dev.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.travel.model.City;

public interface CityRepository extends JpaRepository<City, Long> {
    
}
