package com.francobbs.jogoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class JogoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(JogoServiceApplication.class, args);
	}

}