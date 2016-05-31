package com.myee.domain.restaurant.model;

import com.myee.domain.AbstractEntity;

import javax.persistence.*;

@Entity
@javax.persistence.Table(name = "r_table")
public class Table extends AbstractEntity {
    @Id
    @Column(name = "r_table_id", columnDefinition = "INT")
    @TableGenerator(name = "PkGen_120", table = "ad_sequence", pkColumnName = "name", pkColumnValue = "R_Table", valueColumnName = "currentnextsys", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "PkGen_120")
    private Long   tableID;
    @Column(name = "r_table_type_id")
    private long   tableTypeID;
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

    public Long getTableZoneID() {
        return tableZoneID;
    }

    public void setTableZoneID(Long tableZoneID) {
        this.tableZoneID = tableZoneID;
    }

    public Long getTableID() {
        return tableID;
    }

    public void setTableID(Long tableID) {
        this.tableID = tableID;
    }

    public long getTableTypeID() {
        return tableTypeID;
    }

    public void setTableTypeID(long tableTypeID) {
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

}
