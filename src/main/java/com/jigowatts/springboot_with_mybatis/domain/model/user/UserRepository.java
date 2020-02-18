package com.jigowatts.springboot_with_mybatis.domain.model.user;

/**
 * UserRepository
 */
public interface UserRepository {

    User findOne(String id);
}