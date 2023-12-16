package com.thesis.bus_station.mapper;


import com.thesis.bus_station.dto.BusSeatDTO;
import com.thesis.bus_station.model.users.BusRide;
import com.thesis.bus_station.model.users.BusSeat;
import com.thesis.bus_station.model.users.BusUser;

public class BusSeatMapper {

    public BusSeat busSeatDTOToBusSeat(Long id, BusSeatDTO busSeatDTO, BusUser user, double price){
        return new BusSeat(id,busSeatDTO.bus_id,busSeatDTO.seat_num,user.getId(),busSeatDTO.getTicket_state(),busSeatDTO.return_tic,price,busSeatDTO.type_of_discount);
    }
}
