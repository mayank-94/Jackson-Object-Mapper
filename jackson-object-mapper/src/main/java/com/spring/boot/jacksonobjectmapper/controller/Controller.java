package com.spring.boot.jacksonobjectmapper.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
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
		String carJson = MapperUtility.write(car);
		log.info("This is Car Object after serialization {}", carJson);
	}
	
	@GetMapping("/object")
	public void read() {
		String json = "{\"name\":\"Verna\", \"brand\":\"Hyundai\", \"type\":\"Sedan\"}";
		Car car = MapperUtility.readValue(json, Car.class);
		log.info("Java Object after deserialization is {}", car);
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
	
	@GetMapping("/list-serialize")
	public void listWrite() {
		List<Car> cars = Arrays.asList(
				new Car("Verna", "Hyundai"),
				new Car("Eco Sport", "Hyundai")
			);
		String carsJson = MapperUtility.write(cars);
		log.info("List of cars after serialization is {}", carsJson);
	}
	
	@GetMapping("/list-deserialize")
	public void listRead() {
		String cars = "[{\"name\":\"Verna\",\"brand\":\"Hyundai\"},{\"name\":\"Eco Sport\",\"brand\":\"Hyundai\"}]";
		List<Car> carList = MapperUtility.readValue(cars, new TypeReference<ArrayList<Car>>() {});
		log.info("List of cars after desialization is {}", carList);
	}
}
