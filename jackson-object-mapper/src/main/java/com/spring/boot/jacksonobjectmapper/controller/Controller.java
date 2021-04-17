package com.spring.boot.jacksonobjectmapper.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.jacksonobjectmapper.dto.EmployeeDTO;
import com.spring.boot.jacksonobjectmapper.pojo.Car;
import com.spring.boot.jacksonobjectmapper.pojo.Employee;
import com.spring.boot.jacksonobjectmapper.util.MapperUtility;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class Controller {
	
	@GetMapping("/string")
	public void write() {
		log.info("Inside method map");
		Car car = Car.builder().name("Creta").brand("Hyundai").build();
		String carJson = MapperUtility.map(car);
		log.info("This is Car Object {}", carJson);
	}
	
	@GetMapping("/object")
	public void read() {
		String json = "{\"name\":\"Verna\", \"brand\":\"Hyundai\", \"type\":\"Sedan\"}";
		Car car = MapperUtility.readValue(json, Car.class);
		log.info("Java Object is {}", car);
	}
	
	@GetMapping("/node")
	public void readAsNode() {
		String json = "{\"name\":\"Verna\", \"brand\":\"Hyundai\", \"type\":\"Sedan\"}";
		MapperUtility.readAsNode(json);
	}
	
	@GetMapping("/convert")
	public void convert() {
		Employee employee = Employee.builder().name("Sierra").age(25).build();
		EmployeeDTO empDTO = MapperUtility.convert(employee, EmployeeDTO.class);
		log.info("Employee DTO is {}", empDTO);
	}
}
