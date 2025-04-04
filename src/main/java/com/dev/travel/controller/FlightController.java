package com.dev.travel.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dev.travel.model.City;
import com.dev.travel.model.Flight;
import com.dev.travel.service.AirplaneService;
import com.dev.travel.service.CityService;
import com.dev.travel.service.FlightService;

@Controller
@RequestMapping("/flight")
public class FlightController {
    
    @Autowired
    private FlightService flightService;

    @Autowired
    private AirplaneService airplaneService;

    @Autowired
    private CityService cityService;

    @GetMapping("/list")
    public String listFlights(Model model) {
        List<Flight> flights = flightService.getAllFlights();
        List<City> cities = cityService.getAllCities();
        model.addAttribute("cities", cities);
        model.addAttribute("flights", flights);
        return "flight/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("airplanes", airplaneService.getAllAirplanes());
        model.addAttribute("cities", cityService.getAllCities());
        model.addAttribute("flight", new Flight());
        return "flight/create";
    }

    @PostMapping("/create")
    public String createFlight(@ModelAttribute("flight") Flight flight) {
        flightService.createFlight(flight);
        return "redirect:/flight/list";
    }

    @GetMapping("/edit/{flight_number}")
    public String showEditForm(@PathVariable("flight_number") Long flightNumber, Model model) {
        Flight flight = flightService.getFlightById(flightNumber);
        if (flight != null) {
            model.addAttribute("flight", flight);
            return "flight/edit";
        }
        return "redirect:/flight/list";
    }

    @PostMapping("/edit")
    public String editFlight(@ModelAttribute("flight") Flight flight) {
        flightService.updateFlight(flight.getFlightNumber(), flight);
        return "redirect:/flight/list";
    }

    @GetMapping("/delete/{flight_number}")
    public String deleteFlight(@PathVariable("flight_number") Long flightNumber) {
        flightService.deleteFlight(flightNumber);
        return "redirect:/flight/list";
    }

    @GetMapping("/searchByDates")
    public String searchFlightsByDates(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            Model model) {
        
        List<Flight> flights;
        
        if (startDate != null && endDate != null) {
            LocalDateTime startDateTime = startDate.atStartOfDay();
            LocalDateTime endDateTime = endDate.atTime(LocalTime.MAX);
            
            flights = flightService.findFlightsByDateRange(startDateTime, endDateTime);
            model.addAttribute("startDate", startDate);
            model.addAttribute("endDate", endDate);
        } else {
            flights = flightService.getAllFlights();
        }
        
        model.addAttribute("flights", flights);
        return "flight/list";
    }

    @GetMapping("/searchByCity")
    public String searchFlightsByCity(@RequestParam(required = false) Long departureCity, Model model) {
        List<Flight> flights;
        List<City> cities = cityService.getAllCities();
        model.addAttribute("cities", cities);
        
        if (departureCity != null) {
            flights = flightService.findFlightsByCity(departureCity);
            model.addAttribute("departureCity", departureCity);
        } else {
            flights = flightService.getAllFlights();
        }
        model.addAttribute("flights", flights);
        return "flight/list";
    }
    
}
