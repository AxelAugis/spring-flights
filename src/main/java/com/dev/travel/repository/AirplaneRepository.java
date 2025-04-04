package com.dev.travel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.travel.model.Airplane;

public interface AirplaneRepository extends JpaRepository<Airplane, Long> {
    List<Airplane>  findAllByOrderByNameAsc();
}
