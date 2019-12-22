package com.jigowatts.springboot_with_mybatis.service;

import com.jigowatts.springboot_with_mybatis.domain.model.Message;
import com.jigowatts.springboot_with_mybatis.repository.MessageRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * MessagesService
 */
@Service
public class MessagesService {

    @Autowired
    MessageRepository messageMapper;

    public List<Message> findAll() {
        return messageMapper.findAll();
    }

    public Message findById(int id) {
        return messageMapper.findOne(id);
    }

    public long count() {
        return messageMapper.count();
    }

    public Message create(Message message) {
        messageMapper.create(message);
        return message;
    }

    public Message update(Message message) {
        messageMapper.update(message);
        return message;
    }

    public boolean delete(int id) {
        return messageMapper.delete(id);
    }

}