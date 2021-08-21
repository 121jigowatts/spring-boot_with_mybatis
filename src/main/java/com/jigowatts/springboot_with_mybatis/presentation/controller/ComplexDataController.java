package com.jigowatts.springboot_with_mybatis.presentation.controller;

import com.jigowatts.springboot_with_mybatis.application.service.ComplexDataService;
import com.jigowatts.springboot_with_mybatis.domain.model.complexdata.ComplexData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UsersController
 */
@RestController
@RequestMapping("data")
public class ComplexDataController {

    @Autowired
    ComplexDataService complexDataService;

    @GetMapping(value = "/{id}")
    public ComplexData getDataById(@PathVariable final String id) {
        return complexDataService.findById(id);
    }

}