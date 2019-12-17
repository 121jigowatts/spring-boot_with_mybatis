package com.jigowatts.springboot_with_mybatis.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import com.jigowatts.springboot_with_mybatis.domain.model.Message;
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
    MessagesService messagesService;

    @RequestMapping(method = RequestMethod.GET)
    public List<MessageResource> getMessage() {
        List<Message> messages = messagesService.findAll();
        return messages.stream().map(message -> {
            MessageResource resource = new MessageResource();
            return resource.toResource(message);
        }).collect(Collectors.toList());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public MessageResource getMessageById(@PathVariable final int id) {
        Message message = messagesService.findById(id);
        MessageResource resource = new MessageResource();
        return resource.toResource(message);
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public long getMessageCount() {
        return messagesService.count();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<MessageResource> postMessages(@RequestBody final MessageResource newResource,
            UriComponentsBuilder uriBuilder) {
        Message newMessage = newResource.toEntity();
        messagesService.create(newMessage);

        URI resourceUri = uriBuilder.path("messages/{id}").buildAndExpand(newMessage.getId()).encode().toUri();

        return ResponseEntity.created(resourceUri).build();
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putMessages(@RequestBody final MessageResource resource) {
        Message message = resource.toEntity();
        messagesService.update(message);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMessages(@PathVariable final int id) {
        messagesService.delete(id);
    }
}