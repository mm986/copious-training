package com.copious.training.designpattern;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.OffsetDateTimeSerializer;

import java.time.LocalDate;

/**
 * @author Mahesh More.
 * <p>
 * Singleton class to get Object Mapper instance for mock data` serialization.
 */
public class ObjectMapperSingleton {

    private static ObjectMapperSingleton instance;

    public ObjectMapper mapper;

    /**
     * Private constructor to implement Singleton.
     */
    private ObjectMapperSingleton() {
        mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule()
                        .addSerializer(OffsetDateTimeSerializer.INSTANCE)
                        .addSerializer(LocalDateSerializer.INSTANCE)
                        .addDeserializer(LocalDate.class, LocalDateDeserializer.INSTANCE));
    }

    /**
     * Implementation of thread-safe Singleton instance of Object Mapper.
     */
    public static synchronized ObjectMapperSingleton getInstance() {
        if (instance == null) {
            instance = new ObjectMapperSingleton();
        }
        return instance;
    }
}