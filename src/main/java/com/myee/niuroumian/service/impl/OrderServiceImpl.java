package com.myee.niuroumian.service.impl;

import com.myee.niuroumian.dao.OrderInfoDao;
import com.myee.niuroumian.domain.OrderInfo;
import com.myee.niuroumian.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Jelynn on 2016/6/2.
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderInfoDao orderInfoDao;

    @Transactional
    public OrderInfo createOrder(OrderInfo orderInfo){
        return orderInfoDao.save(orderInfo);
    }

    public int updateOrderState(OrderInfo orderInfo){
        return orderInfoDao.updateOrderState(orderInfo.getUserId(), orderInfo.getOrderId(), orderInfo.getOrderState());
    }

}
