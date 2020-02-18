package com.jigowatts.springboot_with_mybatis.infrastructure.repository;

import com.jigowatts.springboot_with_mybatis.domain.model.user.User;
import com.jigowatts.springboot_with_mybatis.domain.model.user.UserRepository;

import org.springframework.stereotype.Repository;

/**
 * UserImplRepository
 */
@Repository
public class UserImplRepository implements UserRepository {

    private final UserMapper userMapper;

    public UserImplRepository(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User findOne(String id) {
        return userMapper.findOne(id);
    }

}