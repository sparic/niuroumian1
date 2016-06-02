package com.myee.niuroumian.service;

import com.myee.niuroumian.dao.OrderInfoDao;
import com.myee.niuroumian.domain.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Jelynn on 2016/6/1.
 */

public interface OrderService {

     OrderInfo createOrder(OrderInfo orderInfo);

     int updateOrderState(OrderInfo orderInfo);
}
