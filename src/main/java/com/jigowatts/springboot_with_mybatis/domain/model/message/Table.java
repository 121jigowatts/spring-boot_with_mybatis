package com.jigowatts.springboot_with_mybatis.domain.model.message;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Table
 */
public class Table implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @JsonProperty("table_name")
    private String tableName;
    @JsonProperty("columns")
    public List<Column> columns;

    public Table() {

    }

    public Table(String tableName, List<Column> columns) {
        this.tableName = tableName;
        this.columns = columns;
    }

    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<Column> getColumns() {
        return this.columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }
}