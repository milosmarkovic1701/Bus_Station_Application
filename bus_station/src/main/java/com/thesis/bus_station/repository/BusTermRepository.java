package com.thesis.bus_station.repository;

import com.thesis.bus_station.model.users.BusRide;
import com.thesis.bus_station.model.users.BusTerm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BusTermRepository extends JpaRepository<BusTerm,Long> {
//select * from bus_term where (city_ride_id = 1) AND (direction= 'A') AND ((frequency= 'svakodnevno') OR (frequency = 'radnidan')) AND (time_of_departure LIKE '04%')
    @Query(value="SELECT * FROM bus_term where (city_ride_id = :city_ride_id) AND (direction= :direction) AND ((frequency= 'svakodnevno') OR (frequency = :frequency)) AND (time_of_departure LIKE :start%) ",nativeQuery = true)
    List<BusTerm> getTermByBus(@Param("city_ride_id") Long city_ride_id,@Param("direction") String direction, @Param("frequency") String frequency, @Param("start") String start);

}
