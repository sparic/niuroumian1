package com.myee.niuroumian.domain;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 订单明细
 * Created by Jelynn on 2016/6/1.
 */
@Entity
@Table(name = "r_order_item_info")
@DynamicUpdate
public class OrderItemInfo implements Serializable {

    @Id
    @Column(name = "order_item_id")
    @TableGenerator(name = "PkGen_120", table = "ad_sequence", pkColumnName = "name", pkColumnValue = "R_order_item_info", valueColumnName = "currentnextsys", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "PkGen_120")
    private Long orderItemId; //订单明细ID

//    @Column(name = "order_id")
//    private Long orderId;    //商品ID

    @ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE },fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "order_id",referencedColumnName = "order_id")
//    @ManyToOne(targetEntity = OrderInfo.class, optional = false, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
//    @JoinColumn(name = "order_id",referencedColumnName = "order_id")
    private OrderInfo orderInfo; //订单ID

    @Column(name = "dish_id")
    private Long dishId;    //商品ID

    @Column(name = "quantity")
    private int quantity; //商品数量

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    public Long getDishId() {
        return dishId;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
