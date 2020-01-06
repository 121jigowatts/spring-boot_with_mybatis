package com.jigowatts.springboot_with_mybatis.repository;

import java.util.List;

import com.jigowatts.springboot_with_mybatis.domain.model.Message;
import com.jigowatts.springboot_with_mybatis.resource.MessageCriteria;

import org.apache.ibatis.annotations.Mapper;

/**
 * MessageMapper
 */
@Mapper
public interface MessageRepository {
    Message findOne(int id);
    long count();
    List<Message> findAllByCriteria(MessageCriteria criteria);
    
    void create(Message message);
    boolean update(Message message);
    boolean delete(int id);
}