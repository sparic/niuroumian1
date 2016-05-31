package com.myee.domain.restaurant.view;

import com.myee.domain.AbstractView;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@javax.persistence.Table(name = "r_table_zone_v")
public class TableZoneV extends AbstractView {
    @Id
    @Column(name = "r_table_zone_id", columnDefinition = "INT")
    private Long   tableZoneID;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;

    public Long getTableZoneID() {
        return tableZoneID;
    }

    public void setTableZoneID(Long tableZoneID) {
        this.tableZoneID = tableZoneID;
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

}
