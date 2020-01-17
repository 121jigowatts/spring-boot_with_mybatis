package com.jigowatts.springboot_with_mybatis.infrastructure.repository;

import java.util.List;

import com.jigowatts.springboot_with_mybatis.domain.model.message.Message;
import com.jigowatts.springboot_with_mybatis.domain.model.message.MessageCriteria;
import com.jigowatts.springboot_with_mybatis.domain.model.message.MessageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * MessageImplRepository
 */
@Repository
public class MessageImplRepository implements MessageRepository {

    @Autowired
    MessageMapper messageMapper;

    @Override
    public Message findOne(int id) {
        return messageMapper.findOne(id);
    }

    @Override
    public long count() {
        return messageMapper.count();
    }

    @Override
    public List<Message> findAllByCriteria(MessageCriteria criteria) {
        return messageMapper.findAllByCriteria(criteria);
    }

    @Override
    public void create(Message message) {
        messageMapper.create(message);
    }

    @Override
    public boolean update(Message message) {
        return messageMapper.update(message);
    }

    @Override
    public boolean delete(int id) {
        return messageMapper.delete(id);
    }

}