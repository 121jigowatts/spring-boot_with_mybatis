package com.jigowatts.springboot_with_mybatis.domain.model.message;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Database
 */
public class Database implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @JsonProperty("key")
    private int key;
    @JsonProperty("database_name")
    private String databaseName;
    // List<Schema> schemas;

    public Database() {

    }

    public Database(int key, String databaseName, List<Schema> schemas) {
        this.key = key;
        this.databaseName = databaseName;
        // this.schemas = schemas;
    }

    public int getKey() {
        return this.key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getDatabaseName() {
        return this.databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    // public List<Schema> getSchemas() {
    // return this.schemas;
    // }

    // public void setSchemas(List<Schema> schemas) {
    // this.schemas = schemas;
    // }
}