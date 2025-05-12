package com.ticketing;

import com.ticketing.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TicketingApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketingApplication.class, args);
	}

	@Bean
	public CommandLineRunner init(UserService userService) {
		return args -> {
			// Create default admin and agent users on startup
			userService.createDefaultUsers();
		};
	}
}