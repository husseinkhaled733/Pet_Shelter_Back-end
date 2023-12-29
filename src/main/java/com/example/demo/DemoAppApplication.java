package com.example.demo;

import Services.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"Controllers", "DAO", "Model", "Services"})
public class DemoAppApplication {
	public static void main(String[] args) {SpringApplication.run(DemoAppApplication.class, args);}

}
