package com.francobbs.lojavideogames;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class LojavideogamesApplication {

	public static void main(String[] args) {
		SpringApplication.run(LojavideogamesApplication.class, args);
	}

}