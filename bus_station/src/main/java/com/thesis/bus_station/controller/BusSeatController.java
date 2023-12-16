package com.thesis.bus_station.controller;

import com.google.zxing.WriterException;
import com.thesis.bus_station.dto.BusSeatDTO;
import com.thesis.bus_station.dto.InterCityBusRideDTO;
import com.thesis.bus_station.mail.MailService;
import com.thesis.bus_station.model.users.BusRide;
import com.thesis.bus_station.model.users.BusSeat;
import com.thesis.bus_station.model.users.BusUser;
import com.thesis.bus_station.qrCode.QRCodeGenerator;
import com.thesis.bus_station.repository.BusRideRepository;
import com.thesis.bus_station.repository.BusSeatRepository;
import com.thesis.bus_station.repository.UserRepository;
import com.thesis.bus_station.service.BusSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/busSeat")
public class BusSeatController {

    @Autowired
    private BusSeatRepository busSeatRepository;
    @Autowired
    private BusSeatService busSeatService;



    @GetMapping(path = "/get")
    public ResponseEntity<List<BusSeat>> getUsers() {
        return new ResponseEntity<List<BusSeat>>(busSeatRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/isSeatReserved/{busId}/{seatNum}")
    public ResponseEntity<Boolean> isSeatReserved(@PathVariable("busId") Long busId, @PathVariable("seatNum") int seatNum) {
        if (busSeatRepository.isSeatReserved(seatNum, busId).isEmpty())
            return new ResponseEntity<Boolean>(false, HttpStatus.OK);
        else
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @PutMapping("/reserveSeat")
    public ResponseEntity<Boolean> reserveSeat(@RequestBody BusSeatDTO busSeatDTO) {
        return new ResponseEntity<>(busSeatService.reserveSeat(busSeatDTO), HttpStatus.OK);
    }

    @GetMapping(path = "/buy/{id}")
    public ResponseEntity<Boolean> buySeat(@PathVariable("id") Long id) {
        System.out.println("nesto");
        return new ResponseEntity<>(busSeatService.buySeat(id), HttpStatus.OK);
    }




}