package it.fabrick.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonUtils {

    private static final ObjectMapper OBJECT_MAPPER = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .build();

    public static <T> T asPojo(String resource, Class<T> clazz) {

        try {
            OBJECT_MAPPER.registerModule(new JavaTimeModule());
            return OBJECT_MAPPER.readValue(resource, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
