package com.copious.training.util;

import com.copious.training.domain.GenericResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtil {
    public static Object convertJsonToPojo(String jsonString, TypeReference typeRef) throws JsonProcessingException {
        return new ObjectMapper().readValue(jsonString, GenericResponse.class);
    }

    public static String convertPojoToJsonString(Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);
    }
}
