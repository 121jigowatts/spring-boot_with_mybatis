package com.jigowatts.springboot_with_mybatis.util.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Component;

/**
 * JsonConverter
 */
@Component
public class JsonConverter<T> {
    private ObjectMapper objectMapper;

    public JsonConverter() {
        this.objectMapper = new ObjectMapper();
    }

    public T convertToObject(String jsonStr, Class<T> clz) throws IOException {
        return this.objectMapper.readValue(jsonStr, clz);
    }

    public String convertToString(T obj) throws JsonProcessingException {
        return this.objectMapper.writeValueAsString(obj);
    }
}