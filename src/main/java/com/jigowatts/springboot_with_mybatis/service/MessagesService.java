package com.jigowatts.springboot_with_mybatis.service;

import com.jigowatts.springboot_with_mybatis.domain.model.Message;
import com.jigowatts.springboot_with_mybatis.domain.mapper.MessageMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * MessagesService
 */
@Service
public class MessagesService {

    @Autowired
    MessageMapper messageMapper;

    public Message create(Message message) {
        messageMapper.create(message);
        return message;
    }

}