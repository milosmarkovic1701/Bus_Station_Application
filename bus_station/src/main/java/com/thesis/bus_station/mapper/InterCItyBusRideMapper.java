package com.thesis.bus_station.mapper;

import com.thesis.bus_station.dto.InterCityBusRideDTO;
import com.thesis.bus_station.model.users.BusRide;

public class InterCItyBusRideMapper {

   public InterCityBusRideDTO BusRideToInterCityBusRide(BusRide busRide){
        return new InterCityBusRideDTO(busRide.getId(), busRide.getEstimatedTravelTime(), busRide.getBusCompanyName(), busRide.getTimeOfDeparture(),busRide.getDestination(),busRide.getFrequency(),busRide.getPlatform());
   }
}
