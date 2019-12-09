package com.jigowatts.springboot_with_mybatis.domain;

import java.io.Serializable;

/**
 * Message
 */
public class Message implements Serializable {
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

}