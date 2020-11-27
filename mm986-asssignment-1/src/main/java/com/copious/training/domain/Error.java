package com.copious.training.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

/**
 * @author Mahesh More
 * <p>
 * This is model class to store the all Error details for this application.
 */
@Value.Immutable
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = ImmutableError.Builder.class)
public interface Error {
    String getCode();
    String getMessage();
}
