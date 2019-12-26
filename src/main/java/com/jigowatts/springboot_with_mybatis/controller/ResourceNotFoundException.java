package com.jigowatts.springboot_with_mybatis.controller;

/**
 * ResourceNotFoundException
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    ResourceNotFoundException(String message) {
        super(message);
    }
}