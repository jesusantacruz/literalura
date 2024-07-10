package com.aluracursos.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataConverter  {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public <T> T transformData(String json, Class<T> clase) {
        try {
            return objectMapper.readValue(json, clase);
        } catch (JsonProcessingException e) {
            System.err.println("Error al procesar el JSON: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
