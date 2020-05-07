package com.jigowatts.springboot_with_mybatis.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import com.jigowatts.springboot_with_mybatis.domain.model.message.Message;
import com.jigowatts.springboot_with_mybatis.domain.model.message.MessageCriteria;

import org.apache.ibatis.annotations.Mapper;

/**
 * MessageMapper
 */
@Mapper
public interface MessageMapper {
    Optional<Message> findOne(int id);
    long count();
    List<Message> findAllByCriteria(MessageCriteria criteria);
    
    void create(Message message);
    boolean update(Message message);
    boolean delete(int id);
}