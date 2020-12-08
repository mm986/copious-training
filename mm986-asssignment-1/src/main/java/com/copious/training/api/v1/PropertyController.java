package com.copious.training.api.v1;

import com.copious.training.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

/**
 * @author Mahesh More
 * <p>
 * Controller class to operate on Properties class in java8
 */
@RestController
public class PropertyController {

    @Autowired
    PropertyService propertyService;

    /**
     * API to get all System Properties.
     *
     * @return Sku's
     * @throws IOException
     */
    @GetMapping(value = {"/property/system"})
    Map getSystemProperties() {
        return propertyService.getSystemProperties();
    }
}
