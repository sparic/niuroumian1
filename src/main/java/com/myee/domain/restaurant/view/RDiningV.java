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
@javax.persistence.Table(name = "r_dining_v")
public class RDiningV extends AbstractView {
    @Id
    @Column(name = "r_dining_id", columnDefinition = "INT")
    private Long    diningID;
    @Column(name = "r_table_type_id")
    private long    tableTypeId;
    @Column(name = "r_table_id")
    private long    tableId;
    @Column(name = "time_seated")
    private Date    timeSeated;
    @Column(name = "time_left")
    private Date    timeLeft;
    @Column(name = "date_dining")
    private Date    dateDining;
    @Column(name = "table_type_name")
    private String  tableTypeName;
    @Column(name = "call_number")
    private String  callNumber;
    @Column(name = "diner_count")
    private Long    dinerCount;
    @Column(name = "plan_queuing_id")
    private Long    planQueuingId;
    @Column(name = "table_name")
    private String  tableName;
    @Column(name = "table_capacity", columnDefinition = "INT")
    private Long    tableCapacity;
    @Column(name = "zone_name")
    private String  zoneName;
    @Column(name = "eat_minutes")
    private Integer eatMinutes;
    @Column(name = "time_next_seated")
    private Date timeNextSeated;
    @Column(name = "trun_minutes")
    private Integer trunMinutes;

    public Date getTimeNextSeated() {
        return timeNextSeated;
    }

    public void setTimeNextSeated(Date timeNextSeated) {
        this.timeNextSeated = timeNextSeated;
    }

    public Integer getTrunMinutes() {
        return trunMinutes;
    }

    public void setTrunMinutes(Integer trunMinutes) {
        this.trunMinutes = trunMinutes;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public Long getDiningID() {
        return diningID;
    }

    public void setDiningID(Long diningID) {
        this.diningID = diningID;
    }

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

    public String getTimeSeated() {
        if (null != timeSeated) {
            return fmtTime.print(timeSeated.getTime());
        }
        return null;
    }

    public Date timeSeated() {
        return timeSeated;
    }

    public Integer getEatMinutes() {
        return eatMinutes;
    }

    public void setEatMinutes(Integer eatMinutes) {
        this.eatMinutes = eatMinutes;
    }

    public void setTimeSeated(Date timeSeated) {
        this.timeSeated = timeSeated;
    }

    public String getTimeLeft() {
        if (null != timeLeft) {
            return fmtTime.print(timeLeft.getTime());
        }
        return null;
    }

    public void setTimeLeft(Date timeLeft) {
        this.timeLeft = timeLeft;
    }

    transient DateTimeFormatter fmtTime = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

    public Date getDateDining() {
        return dateDining;
    }

    public void setDateDining(Date dateDining) {
        this.dateDining = dateDining;
    }

    public String getTableTypeName() {
        return tableTypeName;
    }

    public void setTableTypeName(String tableTypeName) {
        this.tableTypeName = tableTypeName;
    }

    public String getCallNumber() {
        return callNumber;
    }

    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }

    public Long getDinerCount() {
        return dinerCount;
    }

    public void setDinerCount(Long dinerCount) {
        this.dinerCount = dinerCount;
    }

    public Long getPlanQueuingId() {
        return planQueuingId;
    }

    public void setPlanQueuingId(Long planQueuingId) {
        this.planQueuingId = planQueuingId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Long getTableCapacity() {
        return tableCapacity;
    }

    public void setTableCapacity(Long tableCapacity) {
        this.tableCapacity = tableCapacity;
    }
}
