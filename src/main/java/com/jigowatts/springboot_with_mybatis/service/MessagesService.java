package com.jigowatts.springboot_with_mybatis.service;

import com.jigowatts.springboot_with_mybatis.domain.model.Message;
import com.jigowatts.springboot_with_mybatis.repository.MessageRepository;
import com.jigowatts.springboot_with_mybatis.resource.MessageCriteria;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * MessagesService
 */
@Service
public class MessagesService {

    @Autowired
    MessageRepository messageRepository;

    public List<Message> findAllByCriteria(MessageCriteria criteria) {
        return messageRepository.findAllByCriteria(criteria);
    }

    public Message findById(int id) {
        return messageRepository.findOne(id);
    }

    public long count() {
        return messageRepository.count();
    }

    public Message create(Message message) {
        messageRepository.create(message);
        return message;
    }

    public Message update(Message message) {
        messageRepository.update(message);
        return message;
    }

    public boolean delete(int id) {
        return messageRepository.delete(id);
    }

}