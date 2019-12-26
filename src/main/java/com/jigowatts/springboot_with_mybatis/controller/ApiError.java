package com.jigowatts.springboot_with_mybatis.controller;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ApiError
 */
public class ApiError implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String message;

    @JsonProperty("documentation_url")
    private String documentationUrl;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDocumentationUrl() {
        return this.documentationUrl;
    }

    public void setDocumentationUrl(String documentationUrl) {
        this.documentationUrl = documentationUrl;
    }
    
}