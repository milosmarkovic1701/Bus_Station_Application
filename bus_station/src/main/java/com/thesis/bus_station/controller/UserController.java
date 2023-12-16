package com.thesis.bus_station.controller;

import com.thesis.bus_station.dto.LogInDTO;
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

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/busUser")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping(path="/get")
    public ResponseEntity<List<BusUser>> getUsers()
    {
        return new ResponseEntity<List<BusUser>>(userRepository.findAll(), HttpStatus.OK);
    }
    @PostMapping(path="/login")
    public ResponseEntity<LogInDTO> login(@RequestBody LogInDTO credentials){
        List<BusUser> users = userRepository.findAll();
        System.out.println(credentials.toString());
        System.out.println(users.size());

        for (BusUser user :users) {
            if(user.getUsername().equals(credentials.getUsername()) && user.getPassword().equals(credentials.getPassword())) {
                System.out.println("gas");
                credentials.setRole(user.getUserRole());
                System.out.println(credentials.toString());
                return new ResponseEntity<LogInDTO>(credentials, HttpStatus.OK);
            }
        }
        System.out.println("gas2");
        return new ResponseEntity<LogInDTO>(credentials, HttpStatus.BAD_REQUEST);
    }
}
