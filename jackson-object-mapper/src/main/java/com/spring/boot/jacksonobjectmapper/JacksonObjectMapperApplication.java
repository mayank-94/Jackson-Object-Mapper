package com.spring.boot.jacksonobjectmapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spring.boot.jacksonobjectmapper.pojo.Car;
import com.spring.boot.jacksonobjectmapper.util.MapperUtility;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class JacksonObjectMapperApplication implements CommandLineRunner{
	
	@Autowired
	private MapperUtility<Car> mapperUtility;
	
	public static void main(String[] args) {
		SpringApplication.run(JacksonObjectMapperApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Car car = Car.builder().name("Verna").brand("Hyundai").build();
		//mapperUtility.writeValue(car);
		mapperUtility.writeValueAsString(car);
		
		String json = "{\"name\":\"Verna\", \"brand\":\"Hyundai\", \"type\":\"Sedan\"}";
		mapperUtility.readValue(json);
		
		mapperUtility.readAsNode(json);
		log.info("---- Mapping is done successfully ----");
	}
}
