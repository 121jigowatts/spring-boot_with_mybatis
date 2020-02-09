package com.jigowatts.springboot_with_mybatis.presentation.resource;

import java.io.IOException;
import java.io.Serializable;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jigowatts.springboot_with_mybatis.domain.model.message.Database;
import com.jigowatts.springboot_with_mybatis.domain.model.message.Message;
import com.jigowatts.springboot_with_mybatis.util.json.JsonConverter;

/**
 * MessageResource
 */
public class MessageResource implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    JsonConverter<Database> jsonConverter = new JsonConverter<>();

    private int id;
    @Size(max = 10)
    private String text;
    private Database jsonbValue;

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

    public Database getJsonbValue() {
        return this.jsonbValue;
    }

    public void setJsonbValue(Database jsonbValue) {
        this.jsonbValue = jsonbValue;
    }

    public Message toEntity() {
        Message entity = new Message();
        entity.setId(this.id);
        entity.setText(this.text);
        Database db = this.jsonbValue;
        String json = "";
        try {
            json = jsonConverter.convertToString(db);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        entity.setJsonbValue(json);
        return entity;
    }

    public MessageResource toResource(Message message) {
        MessageResource resource = new MessageResource();
        resource.setId(message.getId());
        resource.setText(message.getText());
        Database db = null;
        String json = message.getJsonbValue();
        if (json != null) {
            try {
                db = jsonConverter.convertToObject(json, Database.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        resource.setJsonbValue(db);
        return resource;
    }
}