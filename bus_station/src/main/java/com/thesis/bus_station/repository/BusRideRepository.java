package com.thesis.bus_station.repository;

import com.thesis.bus_station.model.users.BusRide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface BusRideRepository extends JpaRepository<BusRide, Long> {
        @Query(value="SELECT * FROM bus_ride  where (time_of_departure BETWEEN :date1 AND :date2) AND (destination = :destination) ORDER BY time_of_departure ASC",nativeQuery = true)
        List<BusRide> getRidesForDate(@Param("date1") LocalDateTime date1,@Param("date2")  LocalDateTime date2,@Param("destination") String destination );

        @Query(value="SELECT * FROM bus_ride  where destination = :destination",nativeQuery = true)
        List<BusRide> getRidesForDestination(@Param("destination") String destination);

        @Query(value="SELECT * FROM bus_ride  where DATE(time_of_departure) = :time_of_departure",nativeQuery = true)
        List<BusRide> getRidesForToday(@Param("time_of_departure") LocalDateTime time_of_depatrure);

        }
