package com.myee.niuroumian.dao;

import com.myee.niuroumian.domain.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Jelynn on 2016/6/1.
 */
public interface OrderInfoDao extends JpaRepository<OrderInfo, Long> {
}
