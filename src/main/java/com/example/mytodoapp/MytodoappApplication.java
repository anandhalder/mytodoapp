package com.example.mytodoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MytodoappApplication {

	public static void main(String[] args) {

		SpringApplication.run(MytodoappApplication.class, args);
	}
}
