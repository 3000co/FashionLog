package com.fashionlog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FashionLogApplication {
	
	public static void main(String[] args) {
		System.out.println("before running app");
		SpringApplication.run(FashionLogApplication.class, args);
		System.out.println("after running app");
		
	}

}
