package com.jigowatts.springboot_with_mybatis.domain.model;

/**
 * Message
 */
public class Message {
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
    
}