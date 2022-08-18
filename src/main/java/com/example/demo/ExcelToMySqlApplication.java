package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExcelToMySqlApplication {
//Apache POI (Java API for Microsoft Documents) ->used to store Microsoft doc data into db
	public static void main(String[] args) {
		SpringApplication.run(ExcelToMySqlApplication.class, args);
	}

}
