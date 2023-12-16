package com.thesis.bus_station.model.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity

public class BusTerm {
    @Id
    Long id;
    @Column
    Long cityRideId;
    @Column
    String frequency;
    @Column
    String direction;
    @Column
    String timeOfDeparture;
}
