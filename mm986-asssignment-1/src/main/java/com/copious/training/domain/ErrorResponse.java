package com.copious.training.domain;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class ErrorResponse implements Serializable {

    private static final Long serialVersionId = 123457L;

    @ApiModelProperty(position = 2,
            value = "Response Message",
            name = "message",
            dataType = "java.lang.String",
            required = true)
    private String errorMessage;

    private String errorDetails;

    public ErrorResponse() {
    }

    public ErrorResponse(String errorMessage, String errorDetails) {
        this.errorMessage = errorMessage;
        this.errorDetails = errorDetails;
    }

    public String getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(String errorDetails) {
        this.errorDetails = errorDetails;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}

