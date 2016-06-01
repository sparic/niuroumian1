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
    private String orderState; //订单状态

    @Column(name = "order_price")
    private BigDecimal orderPrice;  //订单金额

    @Column(name = "pay_state")
    private String payState;  //支付状态

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

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getPayState() {
        return payState;
    }

    public void setPayState(String payState) {
        this.payState = payState;
    }
}
