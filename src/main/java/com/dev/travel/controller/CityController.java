package com.dev.travel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dev.travel.model.City;
import com.dev.travel.service.CityService;

@Controller
@RequestMapping("/city")
public class CityController {
    @Autowired
    private CityService cityService;

    @GetMapping("/list")
    public String listCities(Model model) {
        List<City> cities = cityService.getAllCities();
        model.addAttribute("cities", cities);
        return "city/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("city", new City());
        return "city/create";
    }

    @PostMapping("/create")
    public String createCity(@ModelAttribute("city")  City city) {
        cityService.createCity(city);
        return "redirect:/city/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        City city = cityService.getCityById(id);
        if (city != null) {
            model.addAttribute("city", city);
            return "city/edit";
        }
        return "redirect:/city/list";
    }

    @PostMapping("/edit")
    public String editCity(@ModelAttribute("city") City city) {
        cityService.updateCity(city.getId(), city);
        return "redirect:/city/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteCity(@PathVariable("id") Long id) {
        cityService.deleteCity(id);
        return "redirect:/city/list";
    }
}
