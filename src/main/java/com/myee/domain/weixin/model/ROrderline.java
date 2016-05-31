package com.myee.domain.weixin.model;

import com.myee.domain.AbstractEntity;

import javax.persistence.*;

/**
 * Created by Martin on 2016/1/18.
 */
@Entity
@Table(name = "r_orderline")
public class ROrderline extends AbstractEntity {
    @Id
    @Column(name = "r_order_line_id", columnDefinition = "INT")
    @TableGenerator(name = "PkGen_134", table = "ad_sequence", pkColumnName = "name", pkColumnValue = "R_Orderline_ID", valueColumnName = "currentnextsys", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "PkGen_134")
    private Long orderlineId;
    @Column(name = "r_order_id")
    private Long orderId;
}
