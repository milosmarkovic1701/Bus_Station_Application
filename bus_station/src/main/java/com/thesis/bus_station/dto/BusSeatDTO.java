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
public class BusSeatDTO {

    public Long bus_id;
    public int seat_num;
    public String username;
    public boolean return_tic;
    public String type_of_discount;
    public double price;
    public String ticket_state;
}
