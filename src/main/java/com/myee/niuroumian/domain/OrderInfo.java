package com.myee.niuroumian.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 订单实体
 * Created by Jelynn on 2016/6/1.
 */
@Entity
@Table(name = "order_info")
public class OrderInfo implements Serializable {

    @Id
    @Column(name = "order_id")
    @TableGenerator(name = "PkGen_120", table = "ad_sequence", pkColumnName = "name", pkColumnValue = "R_order_info", valueColumnName = "currentnextsys", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "PkGen_120")
    private Long orderId; //订单ID

    @Column(name = "shop_id")
    private Long shopId;   //商铺ID

    @Column(name = "user_id")
    private Long userId;    //用户ID

    @Column(name = "dish_id")
    private Long dishId;    //商品ID

    @Column(name = "create_time")
    private Timestamp createTime;   //创建时间

    @Column(name = "update_time")
    private Timestamp updateTime; //更新时间

    @Column(name = "order_time")
    private int orderState; //订单状态

    @Column(name="order_type")
    private int orderType; //订单类型.  6.线上点单  7.线下点单

    @Column(name = "order_price")
    private BigDecimal orderPrice;  //订单金额

    @Column(name = "pay_state")
    private int payState;  //支付状态

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDishId() {
        return dishId;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public int getOrderState() {
        return orderState;
    }

    public void setOrderState(int orderState) {
        this.orderState = orderState;
    }

    public int getPayState() {
        return payState;
    }

    public void setPayState(int payState) {
        this.payState = payState;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "orderId=" + orderId +
                ", shopId=" + shopId +
                ", userId=" + userId +
                ", dishId=" + dishId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", orderState=" + orderState +
                ", orderType=" + orderType +
                ", orderPrice=" + orderPrice +
                ", payState=" + payState +
                '}';
    }
}
