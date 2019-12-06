package com.jigowatts.springboot_with_mybatis.controller;

import java.util.List;

import com.jigowatts.springboot_with_mybatis.domain.Message;
import com.jigowatts.springboot_with_mybatis.mapper.MessageMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * MessagesController
 */
@RestController
@RequestMapping("messages")
public class MessagesController {
    @Autowired
    MessageMapper messageMapper;

    @RequestMapping(method = RequestMethod.GET)
    public List<Message> getMessage() {
        return messageMapper.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Message postMessages(@RequestBody Message message) {
        messageMapper.create(message);
        return message;
    }

}