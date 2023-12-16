package com.thesis.bus_station;

import com.thesis.bus_station.controller.ScheduledTasks;
import com.thesis.bus_station.model.users.BusUser;
import com.thesis.bus_station.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAutoConfiguration
@Import(ScheduledTasks.class)
@ComponentScan
@EnableScheduling
public class BusStationApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusStationApplication.class, args);
	}

}
