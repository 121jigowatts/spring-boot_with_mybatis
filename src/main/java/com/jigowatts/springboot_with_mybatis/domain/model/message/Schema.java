package com.jigowatts.springboot_with_mybatis.domain.model.message;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * Schema
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Schema implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String schemaName;
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