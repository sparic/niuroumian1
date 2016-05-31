package com.myee.dto;

import java.util.Date;

/**
 * Created by Martin on 2015/7/5.
 */
public class RDiningVo {
    private long   tableTypeId;
    private long   tableId;
    private Date   timeSeated;
    private Date   timeLeft;
    private Date   dateDining;
    private String planCallNumber;

    public long getTableTypeId() {
        return tableTypeId;
    }

    public void setTableTypeId(long tableTypeId) {
        this.tableTypeId = tableTypeId;
    }

    public long getTableId() {
        return tableId;
    }

    public void setTableId(long tableId) {
        this.tableId = tableId;
    }

    public Date getTimeSeated() {
        return timeSeated;
    }

    public void setTimeSeated(Date timeSeated) {
        this.timeSeated = timeSeated;
    }

    public Date getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(Date timeLeft) {
        this.timeLeft = timeLeft;
    }

    public Date getDateDining() {
        return dateDining;
    }

    public void setDateDining(Date dateDining) {
        this.dateDining = dateDining;
    }

    public String getPlanCallNumber() {
        return planCallNumber;
    }

    public void setPlanCallNumber(String planCallNumber) {
        this.planCallNumber = planCallNumber;
    }
}
