package com.example.demo;

import DAO.DAO;
import DAO.ShelterDAO;
import Model.Shelter;
import Model.ShelterBuilder;
import Model.Staff;
import Model.StaffBuilder;
import Services.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "DAO")
public class DemoAppApplication {

	@Autowired
	private static Service service;

	public DemoAppApplication(Service service){
		DemoAppApplication.service = service;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoAppApplication.class, args);

//		dao.add(
//				new ShelterBuilder()
//						.setName("Alex Shelter")
//						.setCountry("Egypt")
//						.setCity("Alexandria")
//						.setPhone("55667788")
//						.setEmail("shelter@alex.org")
//						.setDetailedAddress("elshatbi, alex")
//						.get()
//		);

//		System.out.println(dao.get(1));

	}

}
