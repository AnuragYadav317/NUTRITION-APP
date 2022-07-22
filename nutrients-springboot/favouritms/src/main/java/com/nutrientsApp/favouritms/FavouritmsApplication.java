package com.nutrientsApp.favouritms;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@EnableDiscoveryClient
@SpringBootApplication
public class FavouritmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FavouritmsApplication.class, args);
	}
	
	
}
