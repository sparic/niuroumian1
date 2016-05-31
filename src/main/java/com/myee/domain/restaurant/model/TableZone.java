package com.myee.domain.restaurant.model;

import com.myee.domain.AbstractEntity;

import javax.persistence.*;

@Entity
@javax.persistence.Table(name = "r_table_zone")
public class TableZone extends AbstractEntity {
    @Id
    @Column(name = "r_table_zone_id", columnDefinition = "INT")
    @TableGenerator(name = "PkGen_122", table = "ad_sequence", pkColumnName = "name", pkColumnValue = "R_Table_Zone", valueColumnName = "currentnextsys", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "PkGen_122")
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
