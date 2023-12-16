package com.thesis.bus_station.model.users;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class BusUser {
    @Id
    private Long id;
    @Column
    private String username;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private String name;
    @Column
    private String lastName;
    @Column
    private String jmbg;
    //enum i za ovo
    @Column
    private String userRole;
    @Column
    private Boolean isEnabled;


}
