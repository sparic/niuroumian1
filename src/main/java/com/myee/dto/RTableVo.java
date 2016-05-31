package com.myee.dto;

import com.myee.domain.restaurant.model.Table;

public class RTableVo {
    private long    typeId;
    private long    tableId;
    private String  tableName;
    private Integer seatAdded;
    private String  beeperNo;
    private Long    zoneId;

    public RTableVo() {
    }

    public RTableVo(Table table) {
        this(table.getTableTypeID(), table.getTableID(), table.getName(), table.getBeeperNo(), table.getTableZoneID());
    }

    public RTableVo(long typeId, long tableId, String tableName, String beeperNo, Long zoneId) {
        super();
        this.typeId = typeId;
        this.tableId = tableId;
        this.tableName = tableName;
        this.beeperNo = beeperNo;
        this.zoneId = zoneId;
    }

    public Long getZoneId() {
        return zoneId;
    }

    public void setZoneId(Long zoneId) {
        this.zoneId = zoneId;
    }

    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }

    public long getTableId() {
        return tableId;
    }

    public void setTableId(long tableId) {
        this.tableId = tableId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Integer getSeatAdded() {
        return seatAdded;
    }

    public void setSeatAdded(Integer seatAdded) {
        this.seatAdded = seatAdded;
    }

    public String getBeeperNo() {
        return beeperNo;
    }

    public void setBeeperNo(String beeperNo) {
        this.beeperNo = beeperNo;
    }

}