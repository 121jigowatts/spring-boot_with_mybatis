package com.jigowatts.springboot_with_mybatis.presentation.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * StubController
 */
@RestController
@RequestMapping("mock")
public class StubController {

    @RequestMapping(value = "/global-error", method = RequestMethod.GET)
    public void globalError() {
        throw new RuntimeException("Runtime Exception");
    }

    @RequestMapping(value = "/not-found", method = RequestMethod.GET)
    public void notFound() {
        throw new ResourceNotFoundException("Resource is not found.");
    }

}