package com.spring.boot.jacksonobjectmapper.util;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.spring.boot.jacksonobjectmapper.pojo.Car;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MapperUtility<T> {
	
	@Autowired
	private ObjectMapper mapper;
	
	public void writeValue(T t) {
		log.info("---- Object Mapper Triggered ----");
		try {
			mapper.writeValue(new File("C:\\Users\\mayankjain02\\Downloads\\"
					+ "jackson-object-mapper\\target\\"+t+".json"), t);
		} catch (IOException e) {
			log.error("--Exception Occurred--");
			e.printStackTrace();
		}
	}
	
	public void writeValueAsString(T t) {
		log.info("---- Object Mapper Triggered ----");
		try {
			String stringObject = mapper.writeValueAsString(t);
			log.info("Java Object as String is {}", stringObject);
		} catch (JsonProcessingException e) {
			log.error("Exception occurred while parsing");
			e.printStackTrace();
		}
	}
	
	public void readValue(String src) {
		Car car = null;
		try {
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			car = mapper.readValue(src, Car.class);
		} catch (JsonProcessingException e) {
			log.error("Exception occurred while parsing");
			e.printStackTrace();
		}
		log.info("String after deserialize is {}", car);
	}
	
	public void readAsNode(String src) {
		try {
			JsonNode node = mapper.readTree(src);
			String name = node.get("name").asText();
			log.info("Car name is {}", name);
		} catch (JsonProcessingException e) {
			log.error("Exception occurred while parsing");
			e.printStackTrace();
		}
	}
}
