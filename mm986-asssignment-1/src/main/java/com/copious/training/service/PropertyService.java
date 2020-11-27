package com.copious.training.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * @author Mahesh More.
 * <p>
 * Service class to demonstrate and operate on Properties on application.
 */
@Service
public class PropertyService {

    /**
     * This method intended to extract all system properties with help of Properties class in Java.
     *
     * @return Property Map
     */
    public Map getSystemProperties() {
        Properties properties = System.getProperties();
        return properties
                .entrySet()
                .stream()
                .collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));
    }
}
