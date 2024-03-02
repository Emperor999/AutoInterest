package com.softbank.auto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EntityScan("com.softbank.common.entity")
public class AutoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutoApplication.class, args);
	}

}
