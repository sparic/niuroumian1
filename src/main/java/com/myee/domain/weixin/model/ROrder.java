package com.myee.domain.weixin.model;

import com.myee.domain.AbstractEntity;

import javax.persistence.*;

/**
 * Created by Martin on 2016/1/18.
 */
@Entity
@Table(name = "r_order")
public class ROrder extends AbstractEntity {
    @Id
    @Column(name = "r_order_id", columnDefinition = "INT")
    @TableGenerator(name = "PkGen_133", table = "ad_sequence", pkColumnName = "name", pkColumnValue = "R_Order_ID", valueColumnName = "currentnextsys", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "PkGen_133")
    private Long orderId;

}
