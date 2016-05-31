package com.myee.domain.restaurant.view;

import com.myee.domain.AbstractView;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@javax.persistence.Table(name = "r_table_type_v")
public class TableTypeV extends AbstractView {
    @Id
    @Column(name = "r_table_type_id", columnDefinition = "INT")
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
