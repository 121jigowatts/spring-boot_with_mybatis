package com.jigowatts.springboot_with_mybatis.resource;

import java.io.Serializable;

import com.jigowatts.springboot_with_mybatis.domain.model.Message;

/**
 * MessageResource
 */
public class MessageResource implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int id;
    private String text;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Message toEntity() {
        Message entity = new Message();
        entity.setId(this.id);
        entity.setText(this.text);
        return entity;
    }

    public MessageResource toResource(Message message) {
        MessageResource resource = new MessageResource();
        resource.setId(message.getId());
        resource.setText(message.getText());
        return resource;
    }
}