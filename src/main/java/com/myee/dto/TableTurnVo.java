package com.myee.dto;

/**
 * Created by Martin on 2015/10/14.
 */
public class TableTurnVo {
    private String beginDate;
    private String endDate;
    private String tableName;
    private String zoneName;
    private String tableTypeName;
    private Object turnTimes;
    private Object eatMinutes;
    private Object turnMinutes;

    public TableTurnVo() {
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Object getEatMinutes() {
        return eatMinutes;
    }

    public void setEatMinutes(Object eatMinutes) {
        this.eatMinutes = eatMinutes;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableTypeName() {
        return tableTypeName;
    }

    public void setTableTypeName(String tableTypeName) {
        this.tableTypeName = tableTypeName;
    }

    public Object getTurnMinutes() {
        return turnMinutes;
    }

    public void setTurnMinutes(Object turnMinutes) {
        this.turnMinutes = turnMinutes;
    }

    public Object getTurnTimes() {
        return turnTimes;
    }

    public void setTurnTimes(Object turnTimes) {
        this.turnTimes = turnTimes;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }
}

