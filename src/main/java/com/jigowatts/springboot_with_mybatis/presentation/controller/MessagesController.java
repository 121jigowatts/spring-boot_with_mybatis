package com.jigowatts.springboot_with_mybatis.presentation.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.jigowatts.springboot_with_mybatis.application.service.MessagesService;
import com.jigowatts.springboot_with_mybatis.domain.model.message.Message;
import com.jigowatts.springboot_with_mybatis.domain.model.message.MessageCriteria;
import com.jigowatts.springboot_with_mybatis.presentation.resource.MessageResource;
import com.jigowatts.springboot_with_mybatis.presentation.resource.MessageResourceQuery;
import com.jigowatts.springboot_with_mybatis.util.converter.MessageResourceConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * MessagesController
 */
@RestController
@RequestMapping("messages")
public class MessagesController {

    @Autowired
    MessagesService messagesService;
    @Autowired
    MessageResourceConverter resourceConverter;

    @GetMapping
    public List<MessageResource> searchMessages(@Valid MessageResourceQuery query) {
        MessageCriteria criteria = MessageCriteria.builder().text(query.getText()).dbName(query.getDatabase())
                .schemaName(query.getSchema()).build();

        List<Message> messages = messagesService.findAllByCriteria(criteria);

        return messages.stream().map(message -> resourceConverter.toResource(message)).collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public MessageResource getMessageById(@PathVariable final int id) {
        Message message = messagesService.findById(id).orElseThrow(() -> new NotFoundException("Message is not found."));
        return resourceConverter.toResource(message);
    }

    @GetMapping(value = "/count")
    public long getMessageCount() {
        return messagesService.count();
    }

    @PostMapping
    public ResponseEntity<MessageResource> postMessages(@Valid @RequestBody final MessageResource newResource,
            UriComponentsBuilder uriBuilder) {
        Message newMessage = resourceConverter.toEntity(newResource);
        messagesService.create(newMessage);

        URI resourceUri = uriBuilder.path("messages/{id}").buildAndExpand(newMessage.getId()).encode().toUri();

        return ResponseEntity.created(resourceUri).build();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putMessages(@Valid @RequestBody final MessageResource resource) {
        Message message = resourceConverter.toEntity(resource);
        messagesService.update(message);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMessages(@PathVariable final int id) {
        messagesService.delete(id);
    }

}