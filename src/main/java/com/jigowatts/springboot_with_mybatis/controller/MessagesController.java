package com.jigowatts.springboot_with_mybatis.controller;

import java.util.List;

import com.jigowatts.springboot_with_mybatis.domain.Message;
import com.jigowatts.springboot_with_mybatis.domain.mapper.MessageMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Message getMessageById(@PathVariable final int id) {
        return messageMapper.findOne(id);
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public long getMessageCount() {
        return messageMapper.count();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Message postMessages(@RequestBody final Message message) {
        messageMapper.create(message);
        return message;
    }

}