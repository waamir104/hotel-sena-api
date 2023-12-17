package dev.waamir.hotelsenaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import dev.waamir.hotelsenaapi.infrastructure.rest.spring.config.TransactionConfig;

@SpringBootApplication
@Import(TransactionConfig.class)
public class HotelSenaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelSenaApiApplication.class, args);
	}

}
