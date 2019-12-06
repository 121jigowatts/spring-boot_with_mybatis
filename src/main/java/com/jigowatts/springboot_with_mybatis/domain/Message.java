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
    private String text;

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

}