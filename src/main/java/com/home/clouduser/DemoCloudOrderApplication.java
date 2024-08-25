package com.home.clouduser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DemoCloudOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoCloudOrderApplication.class, args);
	}

}
