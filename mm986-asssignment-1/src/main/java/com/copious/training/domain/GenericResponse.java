package com.copious.training.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "Generic Response Body", description = "This is generic class response.")
public class GenericResponse<T> implements Serializable {

    private static final Long serialVersionId = 123457L;

    @ApiModelProperty(position = 1,
            value = "Success Flag",
            name = "isSuccessful",
            dataType = "java.lang.Boolean",
            required = true)
    private final Boolean isSuccessful;

    @ApiModelProperty(position = 2,
            value = "Response Message",
            name = "message",
            dataType = "java.lang.String",
            required = true)
    private final String message;

    //TODO Use of transient
    @ApiModelProperty(position = 3,
            value = "Response Payload",
            name = "payload")
    private transient T payload;

    public GenericResponse(Boolean isSuccessful, String message) {
        this.isSuccessful = isSuccessful;
        this.message = message;
    }

    public GenericResponse(Boolean isSuccessful, String message, T payload) {
        this.isSuccessful = isSuccessful;
        this.message = message;
        this.payload = payload;
    }

    public Boolean getSuccessful() {
        return isSuccessful;
    }

    public String getMessage() {
        return message;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "GenericResponse{" +
                "isSuccessful=" + isSuccessful +
                ", message='" + message + '\'' +
                ", payload=" + payload +
                '}';
    }
}
