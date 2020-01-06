package com.jigowatts.springboot_with_mybatis.resource;

import java.io.Serializable;

import javax.validation.constraints.Size;

/**
 * MessageResourceQuery
 */
public class MessageResourceQuery implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Size(max = 10)
    private String text;

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }
}