package com.thesis.bus_station.controller;

import com.thesis.bus_station.model.users.BusTerm;
import com.thesis.bus_station.model.users.BusUser;
import com.thesis.bus_station.model.users.CityBusRide;
import com.thesis.bus_station.repository.BusTermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/term")
public class BusTermController {
    @Autowired
    public BusTermRepository busTermRepository;

    @GetMapping("/get")
    public ResponseEntity<List<BusTerm>> getAll()
    {
        return new ResponseEntity<List<BusTerm>>(busTermRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping("/getTerms/{id}/{frequency}/{direction}")
    public ResponseEntity<List<String>> getAll(@PathVariable("id") Long id,@PathVariable("frequency") String frequency,@PathVariable("direction") String direction)
    {
        List<String> returnVal = new ArrayList<>();
        for(int i =0;i<24;i++){
            String val = "";
            if(i<10)
                val = val + "0" + i;
            else
                val = val + i;
            System.out.println(val);
            if(i<10){
                List<BusTerm> terms = busTermRepository.getTermByBus(id,direction,frequency,"0"+i);
                if(terms.size()==0)
                    continue;
                for (BusTerm term: terms
                     ) {
                    val = val + " " +  term.getTimeOfDeparture().split(":")[1];
                }
            }
            else{
                List<BusTerm> terms = busTermRepository.getTermByBus(id,direction,frequency, ""+i);
                if(terms.size()==0)
                    continue;
                for (BusTerm term: terms
                ) {
                    val = val + " " +  term.getTimeOfDeparture().split(":")[1];
                }
            }
            returnVal.add(val);
            System.out.println(val);
        }
        return new ResponseEntity<List<String>>(returnVal, HttpStatus.OK);
    }
}
