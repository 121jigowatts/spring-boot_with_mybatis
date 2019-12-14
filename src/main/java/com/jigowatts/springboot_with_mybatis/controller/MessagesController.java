package com.jigowatts.springboot_with_mybatis.controller;

import java.net.URI;
import java.util.List;

import com.jigowatts.springboot_with_mybatis.domain.model.Message;
import com.jigowatts.springboot_with_mybatis.domain.mapper.MessageMapper;
import com.jigowatts.springboot_with_mybatis.resource.MessageResource;
import com.jigowatts.springboot_with_mybatis.service.MessagesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * MessagesController
 */
@RestController
@RequestMapping("messages")
public class MessagesController {
    @Autowired
    MessageMapper messageMapper;

    @Autowired
    MessagesService messagesService;

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
    public ResponseEntity<MessageResource> postMessages(@RequestBody final MessageResource newResource,
            UriComponentsBuilder uriBuilder) {
        Message newMessage = newResource.toEntity();
        messagesService.create(newMessage);

        URI resourceUri = uriBuilder
            .path("messages/{id}")
            .buildAndExpand(newMessage.getId())
            .encode()
            .toUri();

        return ResponseEntity.created(resourceUri).build();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Message putMessages(@RequestBody final Message message) {
        messageMapper.update(message);
        return message;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMessages(@PathVariable final int id) {
        messageMapper.delete(id);
    }
}