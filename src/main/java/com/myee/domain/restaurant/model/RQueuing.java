package com.myee.domain.restaurant.model;

import com.myee.domain.AbstractEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Martin on 2015/7/5.
 */
@Entity
@javax.persistence.Table(name = "r_queuing")
public class RQueuing extends AbstractEntity {
    @Id
    @Column(name = "r_queuing_id", columnDefinition = "INT")
    @TableGenerator(name = "PkGen_119", table = "ad_sequence", pkColumnName = "name", pkColumnValue = "R_Queuing", valueColumnName = "currentnextsys", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "PkGen_119")
    private Long   queuingID;
    @Column(name = "call_number")
    private String callNumber;
    @Column(name = "diner_count")
    private int    dinerCount;
    @Column(name = "r_table_type_id")
    private long   tableTypeId;
    @Column(name = "unique_id")
    private long   uniqueId;
    @Column(name = "call_times")
    private int    calledTimes;
    @Column(name = "time_fall_in")
    private Date   timeFallIn;
    @Column(name = "time_fall_out")
    private Date   timeFallOut;
    @Column(name = "wait_state")
    private String waitState;
    @Column(name = "plan_table_id")
    private Long   planTableId;
    @Column(name = "actual_table_id")
    private Long   actualTableId;
    @Column(name = "open_id")
    private String openId;

    public Date getDateQueuing() {
        return timeFallIn;
    }

    public Long getQueuingID() {
        return queuingID;
    }

    public void setQueuingID(Long queuingID) {
        this.queuingID = queuingID;
    }

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

    public long getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(long uniqueId) {
        this.uniqueId = uniqueId;
    }

    public int getCalledTimes() {
        return calledTimes;
    }

    public void setCalledTimes(int calledTimes) {
        this.calledTimes = calledTimes;
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

    public String getWaitState() {
        return waitState;
    }

    public void setWaitState(String waitState) {
        this.waitState = waitState;
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

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
