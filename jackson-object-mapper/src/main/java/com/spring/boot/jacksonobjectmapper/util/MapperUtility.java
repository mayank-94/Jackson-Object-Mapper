package com.spring.boot.jacksonobjectmapper.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class MapperUtility {
	
	private MapperUtility() {}
	
	private static final ObjectMapper mapper = new ObjectMapper()
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	
	public static <T> String map(final T source) {
		log.info("---- Object Mapper Triggered ----");
		String stringObject = "";
		try {
			stringObject = mapper.writeValueAsString(source);
			log.info("---- Object Mapped Successfully ----");
		} catch (final JsonProcessingException e) {
			log.error("Exception occurred while parsing");
		}
		return stringObject;
	}
	
	public static <T> T readValue(final String src, final Class<T> t) {
		try {
			return mapper.readValue(src, t);
		} catch (JsonProcessingException e) {
			log.error("Exception occurred while parsing");
			throw new RuntimeException();
		}
	}
	
	public static void readAsNode(String src) {
		try {
			JsonNode node = mapper.readTree(src);
			String name = node.get("name").asText();
			log.info("Car name is {}", name);
		} catch (JsonProcessingException e) {
			log.error("Exception occurred while parsing");
			e.printStackTrace();
		}
	}
	
	public static <T> T convert(Object from, Class<T> toValue) {
		return mapper.convertValue(from, toValue);
	}
}
