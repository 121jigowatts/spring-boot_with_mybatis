package com.jigowatts.springboot_with_mybatis.domain.mapper;

import java.util.List;

import com.jigowatts.springboot_with_mybatis.domain.Message;

import org.apache.ibatis.annotations.Mapper;

/**
 * MessageMapper
 */
@Mapper
public interface MessageMapper {
    Message findOne(int id);
    long count();
    List<Message> findAll();
    
    void create(Message message);
    boolean update(Message message);
    boolean delete(int id);
}