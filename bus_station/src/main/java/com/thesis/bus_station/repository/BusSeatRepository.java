package com.thesis.bus_station.repository;

import com.thesis.bus_station.model.users.BusRide;
import com.thesis.bus_station.model.users.BusSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface BusSeatRepository extends JpaRepository<BusSeat,Long> {
    @Query(value="SELECT * FROM bus_seat  where (seat_num = :seat_num) AND (bus_id = :bus_id)",nativeQuery = true)
    List<BusSeat> isSeatReserved(@Param("seat_num") int seat_num, @Param("bus_id")  Long bus_id);

    @Query(value="SELECT * FROM bus_seat  where bus_id = :bus_id",nativeQuery = true)
    List<BusSeat> allSeatsOfRide(@Param("bus_id")  Long bus_id);
}
