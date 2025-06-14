package com.example.springproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 建立Spring時，Dependencies choose Spring Web
// .\gradlew.bat bootRun 在 http://localhost:8080/hello 做測試

@SpringBootApplication
public class SpringprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringprojectApplication.class, args);
		System.out.println("Completeee??");
	}
	
}
