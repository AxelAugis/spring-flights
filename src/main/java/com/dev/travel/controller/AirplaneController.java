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

import com.dev.travel.model.Airplane;
import com.dev.travel.service.AirplaneService;


@Controller
@RequestMapping("/airplane")
public class AirplaneController {
    
    @Autowired
    private AirplaneService airplaneService;

    @GetMapping("/list")
    public String listAirplanes(Model model) {
       List<Airplane> airplanes = airplaneService.getAllAirplanes();
       model.addAttribute("airplanes", airplanes);
       return "airplane/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("airplane", new Airplane());
        return "airplane/create";
    }

    @PostMapping("/create")
    public String createAirplane(@ModelAttribute("airplane") Airplane airplane) {
        airplaneService.createAirplane(airplane);
        return "redirect:/airplane/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Airplane airplane = airplaneService.getAirplaneById(id);
        if (airplane != null) {
            model.addAttribute("airplane", airplane);
            return "airplane/edit";
        }
        return "redirect:/airplane/list";
    }

    @PostMapping("/edit")
    public String editAirplane(@ModelAttribute("airplane") Airplane airplane) {
        airplaneService.updateAirplane(airplane.getId(), airplane);
        return "redirect:/airplane/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteAirplane(@PathVariable("id") Long id) {
        airplaneService.deleteAirplane(id);
        return "redirect:/airplane/list";
    }

    @GetMapping("/orderByNameASC")
    public String orderByNameASC(Model model) {
        List<Airplane> airplanes = airplaneService.getAirplanesOrderedByName();
        model.addAttribute("airplanes", airplanes);
        return "airplane/list";
    }
}
