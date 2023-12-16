package com.thesis.bus_station.controller;

import com.thesis.bus_station.dto.InterCityBusRideDTO;
import com.thesis.bus_station.mapper.InterCItyBusRideMapper;
import com.thesis.bus_station.model.users.BusRide;
import com.thesis.bus_station.model.users.BusSeat;
import com.thesis.bus_station.model.users.BusUser;
import com.thesis.bus_station.repository.BusRideRepository;
import com.thesis.bus_station.repository.BusSeatRepository;
import com.thesis.bus_station.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/busRide")
public class BusRideController {
    @Autowired
    private BusRideRepository busRideRepository;
    private InterCItyBusRideMapper interCItyBusRideMapper = new InterCItyBusRideMapper();

    @GetMapping(path="/getInter")
    public ResponseEntity<List<InterCityBusRideDTO>> getAllInterCityBusRides()
    {
        List<BusRide> allRides = busRideRepository.findAll();
        List<InterCityBusRideDTO> interCityRides = new ArrayList<>();
        for (BusRide ride :allRides
             ) {
            if(ride.getTypeOfTravel().equals("medjugradski")){
                InterCityBusRideDTO rideDto = interCItyBusRideMapper.BusRideToInterCityBusRide(ride);
                interCityRides.add(rideDto);
            }

        }
        return new ResponseEntity<List<InterCityBusRideDTO>>(interCityRides, HttpStatus.OK);
    }
    @GetMapping(path="/getInterByDestination/{destination}/")
    public ResponseEntity<List<InterCityBusRideDTO>> getAllInterRidesByDestination(@PathVariable("destination") String destination)
    {
        List<BusRide> allRides = busRideRepository.findAll();
        List<InterCityBusRideDTO> interCityRides = new ArrayList<>();
        for (BusRide ride : busRideRepository.getRidesForDestination(destination)
        ) {
            System.out.println("Gas");
                InterCityBusRideDTO rideDto = interCItyBusRideMapper.BusRideToInterCityBusRide(ride);
                interCityRides.add(rideDto);


        }
        return new ResponseEntity<List<InterCityBusRideDTO>>(interCityRides, HttpStatus.OK);
    }
    @GetMapping(path="/getInterByParams/{date}/{destination}")
    public ResponseEntity<List<InterCityBusRideDTO>> getAllInterRidesByDate(@PathVariable("date") String date,@PathVariable("destination") String destination)
    {
        List<BusRide> allRides = busRideRepository.findAll();
        List<InterCityBusRideDTO> interCityRides = new ArrayList<>();
        LocalDateTime dateDTO = LocalDateTime.of(Integer.parseInt(date.substring(0,4)),
                Integer.parseInt(date.substring(5,7)), Integer.parseInt(date.substring(8,10)),0,0,0);
        System.out.println(dateDTO);
        List<InterCityBusRideDTO> newRides = new ArrayList<>();
        for (BusRide ride : busRideRepository.getRidesForDate(dateDTO,dateDTO.plusHours(23),destination)
        ) {
            InterCityBusRideDTO rideDto = interCItyBusRideMapper.BusRideToInterCityBusRide(ride);
            newRides.add(rideDto);
        }
        return new ResponseEntity<List<InterCityBusRideDTO>>(newRides, HttpStatus.OK);
    }

}
