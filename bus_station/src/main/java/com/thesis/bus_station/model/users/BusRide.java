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
public class BusRide {

    @Id
    private Long id;
    @Column
    private LocalDateTime timeOfDeparture;
    @Column
    private int estimatedTravelTime;
    @Column
    private String busCompanyName;
    @Column
    private String destination;
    @Column
    private String frequency;
    @Column
    private int platform;
    //dodaj enum za ovo studentska karta ili sta vec
    @Column
    private String typeOfTravel;
    @Column
    private double price;

}
