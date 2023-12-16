package com.thesis.bus_station.controller;

import com.thesis.bus_station.model.users.BusUser;
import com.thesis.bus_station.model.users.InnerCities;
import com.thesis.bus_station.model.users.OutterCities;
import com.thesis.bus_station.repository.InnerCitiesRepository;
import com.thesis.bus_station.repository.OutterCitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/cities")
public class CitiesController {

    @Autowired
    public InnerCitiesRepository innerCitiesRepository;
    @Autowired
    public OutterCitiesRepository outterCitiesRepository;

    @GetMapping("/getInner")
    public ResponseEntity<List<InnerCities>> getInner()
    {
        return new ResponseEntity<List<InnerCities>>(innerCitiesRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping("/getOutter")
    public ResponseEntity<List<OutterCities>> getOutter()
    {
        return new ResponseEntity<List<OutterCities>>(outterCitiesRepository.findAll(), HttpStatus.OK);
    }
}
