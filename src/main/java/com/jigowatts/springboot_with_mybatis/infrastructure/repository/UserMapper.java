package com.jigowatts.springboot_with_mybatis.infrastructure.repository;

import com.jigowatts.springboot_with_mybatis.domain.model.user.User;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * UserMapper
 */
@Mapper
public interface UserMapper {
    @Select("SELECT * FROM users WHERE id = #{id}")
    User findOne(String id);
    
}