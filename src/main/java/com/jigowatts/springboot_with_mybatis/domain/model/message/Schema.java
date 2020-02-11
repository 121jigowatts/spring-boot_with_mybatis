package com.jigowatts.springboot_with_mybatis.domain.model.message;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Schema
 */
public class Schema implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @JsonProperty("schema_name")
    private String schemaName;
    @JsonProperty("tables")
    private List<Table> tables;

    public Schema() {

    }

    public Schema(String schemaName, List<Table> tables) {
        this.schemaName = schemaName;
        this.tables = tables;
    }

    public String getSchemaName() {
        return this.schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public List<Table> getTables() {
    return this.tables;
    }

    public void setTables(List<Table> tables) {
    this.tables = tables;
    }
}