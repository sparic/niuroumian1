package com.myee.niuroumian.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.myee.niuroumian.domain.OrderInfo;
import com.myee.niuroumian.domain.OrderItemInfo;
import com.myee.niuroumian.domain.OrderState;
import com.myee.niuroumian.response.ResponseData;
import com.myee.niuroumian.service.OrderService;
import com.myee.niuroumian.service.WeixinService;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * Created by Jelynn on 2016/6/3.
 */
@Controller
@RequestMapping("order")
public class OrderController {

    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    /**
     * 取消订单/传菜
     * @return
     */
    @RequestMapping(value = "/orderCancel")
    @ResponseBody
    public String orderCancel(@RequestParam (value="message") String message) {
        JSONObject object = JSON.parseObject(message);
        if (StringUtils.isNotBlank(message)) {
            OrderInfo orderInfo = new OrderInfo();
            Long orderId = object.getLong("orderId");
            orderInfo.setOrderId(orderId);
            Long shopId = object.getLong("shopId");
            orderInfo.setShopId(shopId);
            int orderState = object.getInteger("orderState");
            orderInfo.setOrderState(orderState);
            int result = orderService.updateOrderState(orderInfo);
            return JSON.toJSONString(ResponseData.successData(result));
        }
        return JSON.toJSONString(ResponseData.errorData("error"));
    }

    /**
     * 线下点单
     * @return
     */
    @RequestMapping(value = "/orderOffline")
    @ResponseBody
    public String orderOffline(@RequestParam (value="orderDetail") String orderDetail) {
        List<OrderItemInfo> orderItemInfoList = new ArrayList<OrderItemInfo>();
        JSONArray jsonArray = JSON.parseArray(orderDetail);
        OrderInfo orderResult = new OrderInfo();
//        Set<OrderItemInfo> items = new HashSet<OrderItemInfo>();
        if(jsonArray.size() > 0){
            OrderInfo orderInfo = new OrderInfo();
            String userId = null;
            Long shopId = null;
            for(int i=0; i<jsonArray.size();i++){
                JSONObject object = jsonArray.getJSONObject(i);
                OrderItemInfo orderItemInfo = new OrderItemInfo();
                userId = object.getString("userId");
                shopId = object.getLong("shopId");
                orderInfo.setUserId(userId);
                orderInfo.setShopId(shopId);
                orderItemInfo.setDishId(object.getLong("dishId"));
//                items.add(orderItemInfo);
                orderItemInfo.setOrderInfo(orderInfo);
                orderInfo.getItems().add(orderItemInfo);
            }

//            orderInfo.setItems(items);
            orderInfo.setCount(orderInfo.getItems().size());
            orderResult = orderService.createOrder(orderInfo);
        }
        if(orderResult != null){
            return JSON.toJSONString(ResponseData.successData(orderResult));
        }
        return JSON.toJSONString(ResponseData.errorData("error"));
    }

    /**
     * 获取订单信息
     * @return
     */
    @RequestMapping(value = "/getOrderInfo")
    @ResponseBody
    public String getOrderInfo(@RequestParam (value="orderId") Long orderId) {
        OrderInfo orderInfo = orderService.getOrderInfo(orderId );
        if(orderInfo != null){
            return JSON.toJSONString(ResponseData.successData(orderInfo));
        }
        return JSON.toJSONString(ResponseData.errorData("error"));
    }
}
