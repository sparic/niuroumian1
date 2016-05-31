package com.myee.domain.weixin.model;

import com.myee.djinn.dto.Repast;
import com.myee.domain.AbstractEntity;
import com.myee.domain.ToDto;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Martin on 2016/1/18.
 */
@Entity
@Table(name = "r_repast")
public class RRepast extends AbstractEntity implements ToDto<Repast> {
    @Id
    @Column(name = "r_repast_id", columnDefinition = "INT")
    @TableGenerator(name = "PkGen_135", table = "ad_sequence", pkColumnName = "name", pkColumnValue = "R_Repast_ID", valueColumnName = "currentnextsys", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "PkGen_135")
    private Long repastId;
    @Column(name = "r_table_type_id")
    private long tableTypeId;
    @Column(name = "r_table_id")
    private long tableId;
    @Column(name = "time_seated")
    private Date timeSeated;
    @Column(name = "time_left")
    private Date timeLeft;
    @Column(name = "r_order_id")
    private Long orderId;
    @Column(name = "r_wait_token_id")
    private Long waitTokenId;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getRepastId() {
        return repastId;
    }

    public void setRepastId(Long repastId) {
        this.repastId = repastId;
    }

    public long getTableId() {
        return tableId;
    }

    public void setTableId(long tableId) {
        this.tableId = tableId;
    }

    public long getTableTypeId() {
        return tableTypeId;
    }

    public void setTableTypeId(long tableTypeId) {
        this.tableTypeId = tableTypeId;
    }

    public Date getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(Date timeLeft) {
        this.timeLeft = timeLeft;
    }

    public Date getTimeSeated() {
        return timeSeated;
    }

    public void setTimeSeated(Date timeSeated) {
        this.timeSeated = timeSeated;
    }

    public Long getWaitTokenId() {
        return waitTokenId;
    }

    public void setWaitTokenId(Long waitTokenId) {
        this.waitTokenId = waitTokenId;
    }

    @Override
    public Repast toDto() {
        Repast repast = new Repast();
        return repast;
    }
}
