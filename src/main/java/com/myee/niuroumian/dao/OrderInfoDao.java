package com.myee.niuroumian.dao;

import com.myee.niuroumian.domain.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * Created by Jelynn on 2016/6/1.
 */
public interface OrderInfoDao extends JpaRepository<OrderInfo, Long> {

    @Modifying
    @Query("update OrderInfo oi set oi.orderState = ?3 where oi.userId = ?1 and oi.orderId = ?2")
    @Transactional
    int updateOrderState(Long userId, Long orderId,int orderState);

}
