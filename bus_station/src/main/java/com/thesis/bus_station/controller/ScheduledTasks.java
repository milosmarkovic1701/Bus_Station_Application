package com.thesis.bus_station.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.thesis.bus_station.service.BusSeatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
@Component
public class ScheduledTasks {
    @Autowired
    BusSeatService busSeatService;

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 300000)
    public void reportCurrentTime() {
        busSeatService.deleteSeats();
        System.out.println("The time is now " + dateFormat.format(new Date()));
    }
}