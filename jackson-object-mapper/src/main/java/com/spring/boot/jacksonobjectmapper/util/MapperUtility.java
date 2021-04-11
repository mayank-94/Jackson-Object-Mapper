package com.spring.boot.jacksonobjectmapper.util;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	public String writeValueAsString(T t) {
		log.info("---- Object Mapper Triggered ----");
		String stringObject = null;
		try {
			stringObject = mapper.writeValueAsString(t);
			log.info("Java Object as String is {}", stringObject);
		} catch (JsonProcessingException e) {
			log.error("Exception occurred while parsing");
			throw new RuntimeException();
		}
		return stringObject;
	}
	
	public T readValue(String src, Class<T> t) {
		try {
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapper.readValue(src, t);
		} catch (JsonProcessingException e) {
			log.error("Exception occurred while parsing");
			throw new RuntimeException();
		}
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
