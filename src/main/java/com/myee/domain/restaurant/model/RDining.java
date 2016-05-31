package com.myee.domain.restaurant.model;

import com.myee.domain.AbstractEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Martin on 2015/7/5.
 */
@Entity
@javax.persistence.Table(name = "r_dining")
public class RDining extends AbstractEntity {
    @Id
    @Column(name = "r_dining_id", columnDefinition = "INT")
    @TableGenerator(name = "PkGen_118", table = "ad_sequence", pkColumnName = "name", pkColumnValue = "R_Dining", valueColumnName = "currentnextsys", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "PkGen_118")
    private Long diningID;
    @Column(name = "r_table_type_id")
    private long tableTypeId;
    @Column(name = "r_table_id")
    private long tableId;
    @Column(name = "time_seated")
    private Date timeSeated;
    @Column(name = "time_left")
    private Date timeLeft;
    @Column(name = "date_dining")
    private Date dateDining;
    @Column(name = "plan_queuing_id")
    private Long planQueuingId;
    @Column(name = "unique_id")
    private long   uniqueId;
    @Column(name = "time_next_seated")
    private Date timeNextSeated;

    public Date getTimeNextSeated() {
        return timeNextSeated;
    }

    public void setTimeNextSeated(Date timeNextSeated) {
        this.timeNextSeated = timeNextSeated;
    }

    public long getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(long uniqueId) {
        this.uniqueId = uniqueId;
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

    public Long getPlanQueuingId() {
        return planQueuingId;
    }

    public void setPlanQueuingId(Long planQueuingId) {
        this.planQueuingId = planQueuingId;
    }
}