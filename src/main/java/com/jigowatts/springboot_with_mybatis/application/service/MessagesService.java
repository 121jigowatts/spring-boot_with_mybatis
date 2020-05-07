package com.jigowatts.springboot_with_mybatis.application.service;

import com.jigowatts.springboot_with_mybatis.domain.model.message.Message;
import com.jigowatts.springboot_with_mybatis.domain.model.message.MessageCriteria;
import com.jigowatts.springboot_with_mybatis.infrastructure.repository.MessageImplRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * MessagesService
 */
@Service
public class MessagesService {

    @Autowired
    MessageImplRepository messageRepository;

    public List<Message> findAllByCriteria(MessageCriteria criteria) {
        return messageRepository.findAllByCriteria(criteria);
    }

    public Optional<Message> findById(int id) {
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