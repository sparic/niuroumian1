package com.myee.niuroumian.service.order;

import com.myee.niuroumian.dao.OrderInfoDao;
import com.myee.niuroumian.domain.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Jelynn on 2016/6/1.
 */
@Service
public class OrderService {

    @Autowired
    private OrderInfoDao orderInfoDao;

    @Transactional
    public OrderInfo createOrder(OrderInfo orderInfo){
        return orderInfoDao.save(orderInfo);
    }
}
