package com.myee.domain.restaurant.view;

import com.myee.domain.AbstractView;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Martin on 2015/7/5.
 */
@Entity
@javax.persistence.Table(name = "r_queuing_v")
public class RQueuingV extends AbstractView {
    @Id
    @Column(name = "r_queuing_id", columnDefinition = "INT")
    private Long   queuingID;
    @Column(name = "call_number")
    private String callNumber;
    @Column(name = "diner_count")
    private int    dinerCount;
    @Column(name = "r_table_type_id")
    private long   tableTypeId;
    @Column(name = "unique_id")
    private long   uniqueId;
    @Column(name = "called_times")
    private int    calledTimes;
    @Column(name = "time_fall_in")
    private Date   timeFallIn;
    @Column(name = "time_fall_out")
    private Date   timeFallOut;
    @Column(name = "wait_state")
    private String waitState;
    @Column(name = "plan_table_name")
    private String planTableName;
    @Column(name = "actual_table_name")
    private String actualTableName;
    @Column(name = "type_name")
    private String tableTypeName;
    @Column(name = "wait_minutes")
    private Long waitMinutes;

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

    public Long getWaitMinutes() {
        return waitMinutes;
    }

    public void setWaitMinutes(Long waitMinutes) {
        this.waitMinutes = waitMinutes;
    }

    transient DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

    public String getTimeFallIn() {
        if (null != timeFallIn) {
            return formatter.print(timeFallIn.getTime());
        }
        return null;
    }

    public void setTimeFallIn(Date timeFallIn) {
        this.timeFallIn = timeFallIn;
    }

    public String getTimeFallOut() {
        if (null != timeFallOut) {
            return formatter.print(timeFallOut.getTime());
        }
        return null;
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

    public String getPlanTableName() {
        return planTableName;
    }

    public void setPlanTableName(String planTableName) {
        this.planTableName = planTableName;
    }

    public String getActualTableName() {
        return actualTableName;
    }

    public void setActualTableName(String actualTableName) {
        this.actualTableName = actualTableName;
    }

    public String getTableTypeName() {
        return tableTypeName;
    }

    public void setTableTypeName(String tableTypeName) {
        this.tableTypeName = tableTypeName;
    }
}
