package com.abani.microservice.discussionboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DiscussApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscussApiGatewayApplication.class, args);
	}

}
