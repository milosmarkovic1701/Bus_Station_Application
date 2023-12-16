package com.thesis.bus_station.model.users;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class BusSeat {

    @Id
    private Long id;
    @Column
    private Long busId;
    @Column
    private int seatNum;
    @Column
    private Long userId;
    @Column
    private String ticketState;
    @Column
    private Boolean returnTicket;
    @Column
    private double price;
    @Column
    private String discount;


}
