package com.techie.microservices.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication; //Mengimpor Spring Boot untuk konfigurasi otomatis

@SpringBootApplication //Menandakan bahwa ini adalah aplikasi Spring Boot
public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

}
