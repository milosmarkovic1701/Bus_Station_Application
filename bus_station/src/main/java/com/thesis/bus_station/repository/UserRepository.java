package com.thesis.bus_station.repository;

import com.thesis.bus_station.model.users.BusSeat;
import com.thesis.bus_station.model.users.BusUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<BusUser,Long> {

    @Query(value="SELECT * FROM bus_user  where username = :username",nativeQuery = true)
    BusUser getByUsername(@Param("username") String username);
}
