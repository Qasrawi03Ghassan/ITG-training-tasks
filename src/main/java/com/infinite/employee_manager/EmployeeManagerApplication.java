package com.infinite.employee_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class EmployeeManagerApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
		System.setProperty("DB_PASS", dotenv.get("DB_PASS"));
		System.setProperty("sender_email_username", dotenv.get("sender_email_username"));
		System.setProperty("sender_email_password", dotenv.get("sender_email_password"));

		SpringApplication.run(EmployeeManagerApplication.class, args);
	}
}
