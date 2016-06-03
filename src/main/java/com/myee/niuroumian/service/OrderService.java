package com.myee.niuroumian.service;

import com.myee.niuroumian.dao.OrderInfoDao;
import com.myee.niuroumian.domain.OrderInfo;
import com.myee.niuroumian.domain.OrderItemInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Jelynn on 2016/6/1.
 */

public interface OrderService {

     OrderInfo createOrder(OrderInfo orderInfo);

     int updateOrderState(OrderInfo orderInfo);

     OrderInfo getOrderInfo(Long orderId);
}
