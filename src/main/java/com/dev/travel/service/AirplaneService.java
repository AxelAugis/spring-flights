package com.dev.travel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.travel.model.Airplane;
import com.dev.travel.repository.AirplaneRepository;

@Service
public class AirplaneService {
    @Autowired
    private AirplaneRepository airplaneRepository;

    public List<Airplane> getAllAirplanes() {
        return airplaneRepository.findAll();
    }

    public Airplane getAirplaneById(Long id) {
        return airplaneRepository.findById(id).orElse(null);
    }

    public Airplane createAirplane(Airplane airplane) {
        return airplaneRepository.save(airplane);
    }

    public Airplane updateAirplane(Long id, Airplane airplane) {
        if(airplane != null) {
            airplane.setName(airplane.getName());
            return airplaneRepository.save(airplane);
        }
        return null;
    }

    public void deleteAirplane(Long id) {
        airplaneRepository.deleteById(id);
    }

    public List<Airplane> getAirplanesOrderedByName() {
        return airplaneRepository.findAllByOrderByNameAsc();
    }
    
}
