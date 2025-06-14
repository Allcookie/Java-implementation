package com.example.springproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
// .\gradlew.bat bootRun // 測試
public class SpringprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringprojectApplication.class, args);
	}
	@GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "Guest") String name) {
      return String.format("Hello %s!!", name);
    }
	
}
