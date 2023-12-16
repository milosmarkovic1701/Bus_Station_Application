package com.thesis.bus_station.controller;

import com.thesis.bus_station.model.users.BusTerm;
import com.thesis.bus_station.model.users.CityBusRide;
import com.thesis.bus_station.repository.BusTermRepository;
import com.thesis.bus_station.repository.CityBusRideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/cityBusRide")
public class CityBusRideController {

    @Autowired
    public CityBusRideRepository cityBusRideRepository ;

    @GetMapping("/getAll")
    public ResponseEntity<List<CityBusRide>> getAll()
    {
        return new ResponseEntity<List<CityBusRide>>(cityBusRideRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<CityBusRide> getAll(@PathVariable("id") Long id)
    {
        return new ResponseEntity<CityBusRide>(cityBusRideRepository.findById(id).get(), HttpStatus.OK);
    }

}
