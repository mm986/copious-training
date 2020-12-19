package com.copious.training.domain;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class Response implements Serializable {

    private static final Long serialVersionId = 123457L;

    @ApiModelProperty(position = 2,
            value = "Response Message",
            name = "message",
            dataType = "java.lang.String",
            required = true)
    private String message;


    public Response() {
    }

    public Response(String message) {
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

