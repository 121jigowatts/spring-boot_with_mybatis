package com.jigowatts.springboot_with_mybatis.application.service;

import com.jigowatts.springboot_with_mybatis.domain.model.user.User;
import com.jigowatts.springboot_with_mybatis.infrastructure.repository.UserImplRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UsersService
 */
@Service
public class UsersService {

    @Autowired
    UserImplRepository userRepository;

    public User findById(String id) {
        return userRepository.findOne(id);
    }
}