package com.myee.domain.restaurant.view;

import com.myee.domain.AbstractView;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@javax.persistence.Table(name = "r_table_v")
public class TableV extends AbstractView {
    @Id
    @Column(name = "r_table_id", columnDefinition = "INT")
    private Long   tableID;
    @Column(name = "r_table_type_id")
    private Long   tableTypeID;
    @Column(name = "typeName")
    private String typeName;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "seat_added")
    private int    seatAdded;
    @Column(name = "beeper_no")
    private String beeperNo;
    @Column(name = "r_table_zone_id")
    private Long   tableZoneID;
    @Column(name = "zone_name")
    private String zoneName;

    public Long getTableZoneID() {
        return tableZoneID;
    }

    public void setTableZoneID(Long tableZoneID) {
        this.tableZoneID = tableZoneID;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public Long getTableID() {
        return tableID;
    }

    public void setTableID(Long tableID) {
        this.tableID = tableID;
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

    public int getSeatAdded() {
        return seatAdded;
    }

    public void setSeatAdded(int seatAdded) {
        this.seatAdded = seatAdded;
    }

    public String getBeeperNo() {
        return beeperNo;
    }

    public void setBeeperNo(String beeperNo) {
        this.beeperNo = beeperNo;
    }

    public Long getTableTypeID() {
        return tableTypeID;
    }

    public void setTableTypeID(Long tableTypeID) {
        this.tableTypeID = tableTypeID;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

}
