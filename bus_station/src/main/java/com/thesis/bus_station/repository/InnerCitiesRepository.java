package com.thesis.bus_station.repository;

import com.thesis.bus_station.model.users.BusSeat;
import com.thesis.bus_station.model.users.InnerCities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InnerCitiesRepository extends JpaRepository<InnerCities,Long> {

}
