package com.myee.domain.restaurant.model;

import com.myee.domain.AbstractEntity;

import javax.persistence.*;

@Entity
@javax.persistence.Table(name = "r_table_type")
public class TableType extends AbstractEntity {
    @Id
    @Column(name = "r_table_type_id", columnDefinition = "INT")
    @TableGenerator(name = "PkGen_121", table = "ad_sequence", pkColumnName = "name", pkColumnValue = "R_Table_Type", valueColumnName = "currentnextsys", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "PkGen_121")
    private Long   tableTypeID;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "capacity", columnDefinition = "INT")
    private int    capacity;
    @Column(name = "minimum", columnDefinition = "INT")
    private int    minimum;

    public Long getTableTypeID() {
        return tableTypeID;
    }

    public void setTableTypeID(Long tableTypeID) {
        this.tableTypeID = tableTypeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getMinimum() {
        return minimum;
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }
}
