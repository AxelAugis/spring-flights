package com.dev.travel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.travel.model.City;
import com.dev.travel.repository.CityRepository;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public City getCityById(Long id) {
        return cityRepository.findById(id).orElse(null);
    }

    public City createCity(City city) {
        return cityRepository.save(city);
    }

    public City updateCity(Long id, City city) {
        if (city != null) {
            city.setName(city.getName());
            return cityRepository.save(city);
        }
        return null;
    }

    public void deleteCity(Long id) {
        cityRepository.deleteById(id);
    }
}
