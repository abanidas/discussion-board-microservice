package com.abani.microservice.discussionboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DiscussServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscussServiceRegistryApplication.class, args);
	}

}
