package com.jigowatts.springboot_with_mybatis.infrastructure.repository;

import com.jigowatts.springboot_with_mybatis.domain.model.user.User;
import com.jigowatts.springboot_with_mybatis.domain.model.user.UserRepository;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

/**
 * UserImplRepository
 */
@Repository
@RequiredArgsConstructor
public class UserImplRepository implements UserRepository {

    private final UserMapper userMapper;

    @Override
    public User findOne(String id) {
        return userMapper.findOne(id);
    }

}