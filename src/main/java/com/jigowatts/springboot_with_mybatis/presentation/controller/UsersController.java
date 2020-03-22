package com.jigowatts.springboot_with_mybatis.presentation.controller;

import com.jigowatts.springboot_with_mybatis.application.service.UsersService;
import com.jigowatts.springboot_with_mybatis.domain.model.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UsersController
 */
@RestController
@RequestMapping("users")
public class UsersController {

    @Autowired
    UsersService usersService;

    @GetMapping(value = "/{id}")
    public User getUserById(@PathVariable final String id) {
        return usersService.findById(id);
    }

}