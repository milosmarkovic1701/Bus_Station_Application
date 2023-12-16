package com.thesis.bus_station.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InterCityBusRideDTO {

    public Long id;
    public int estimatedTravelTime;
    public String busCompanyName;
    public LocalDateTime timeOfDeparture;
    public String destination;
    public String frequency;
    public int platform;
}
