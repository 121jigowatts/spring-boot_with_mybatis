package com.jigowatts.springboot_with_mybatis.domain.model.message;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * column
 */
public class Column implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @JsonProperty("column_name")
    private String columnName;
    @JsonProperty("sort_order")
    private int sortOrder;

    public Column() {

    }

    public Column(String columnName, int sortOrder) {
        this.columnName = columnName;
        this.sortOrder = sortOrder;
    }

    public String getColumnName() {
        return this.columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public int getSortOrder() {
        return this.sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }
}