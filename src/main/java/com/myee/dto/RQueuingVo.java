package com.myee.dto;

import java.util.Date;

/**
 * Created by Martin on 2015/7/4.
 */
public class RQueuingVo {
    private String callNumber;
    private int    dinerCount;
    private long   tableTypeId;
    private long   uniqueId;
    private int    calledTimes;
    private Date   timeFallIn;
    private Date   timeFallOut;
    private String waitState;
    private Long   planTableId;
    private Long   actualTableId;

    public String getCallNumber() {
        return callNumber;
    }

    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }

    public int getDinerCount() {
        return dinerCount;
    }

    public void setDinerCount(int dinerCount) {
        this.dinerCount = dinerCount;
    }

    public long getTableTypeId() {
        return tableTypeId;
    }

    public void setTableTypeId(long tableTypeId) {
        this.tableTypeId = tableTypeId;
    }

    public String getWaitState() {
        return waitState;
    }

    public void setWaitState(String waitState) {
        this.waitState = waitState;
    }

    public int getCalledTimes() {
        return calledTimes;
    }

    public void setCalledTimes(int calledTimes) {
        this.calledTimes = calledTimes;
    }

    public long getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(long uniqueId) {
        this.uniqueId = uniqueId;
    }

    public Long getPlanTableId() {
        return planTableId;
    }

    public void setPlanTableId(Long planTableId) {
        this.planTableId = planTableId;
    }

    public Long getActualTableId() {
        return actualTableId;
    }

    public void setActualTableId(Long actualTableId) {
        this.actualTableId = actualTableId;
    }

    public Date getTimeFallIn() {
        return timeFallIn;
    }

    public void setTimeFallIn(Date timeFallIn) {
        this.timeFallIn = timeFallIn;
    }

    public Date getTimeFallOut() {
        return timeFallOut;
    }

    public void setTimeFallOut(Date timeFallOut) {
        this.timeFallOut = timeFallOut;
    }
}
