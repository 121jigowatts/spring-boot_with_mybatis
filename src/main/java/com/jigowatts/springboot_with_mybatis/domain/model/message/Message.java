package com.jigowatts.springboot_with_mybatis.domain.model.message;

/**
 * Message
 */
public class Message {
    private int id;
    private String text;
    private String jsonbValue;

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

    public String getJsonbValue() {
        return this.jsonbValue;
    }

    public void setJsonbValue(String jsonbValue) {
        this.jsonbValue = jsonbValue;
    }

}