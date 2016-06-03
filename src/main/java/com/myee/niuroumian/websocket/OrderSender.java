package com.myee.niuroumian.websocket;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.myee.niuroumian.domain.OrderInfo;
import com.myee.niuroumian.domain.OrderState;
import com.myee.niuroumian.response.ResponseData;
import com.myee.niuroumian.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * websocket 推送订单信息,取消订单，线下点单
 * Created by Jelynn on 2016/5/31.
 * //注意此访问地址格式如:
 * "ws://"+ window.location.host+"/${pageContext.request.contextPath}/test/chat"是ws开头的,而不是以http:开头的.
 */
@ServerEndpoint("/niuroumian/sendorder/{userId}")
public class OrderSender {

    private static final Log LOG = LogFactory.getLog(OrderSender.class);

    @Autowired
    private OrderService orderService;

    private static final AtomicInteger connectionIds = new AtomicInteger(0);
    private static final Map<String, Object> connections = new HashMap<String, Object>();

    private static  Long userId;
    private Session session;

//    public OrderSender() {
//        nickname = GUEST_PREFIX + connectionIds.getAndDecrement();
//    }

    /**
     *  打开时调用
     * @param session
     * @param userId
     */
    @OnOpen
    public void start(Session session,@PathParam("userId") Long userId) {
        this.session = session;
        this.userId = userId;

        Object object = connections.get("server");
        if(object == null || "".equals(object.toString())){
            connections.put("server", this);
        }
        connections.put(String.valueOf(userId), this);
        String message = String.format("* %s %s", userId, "has joined");
        LOG.info("Websocket Start Connecting:" + message);
    }

    /**
     * 关闭时调用
     */
    @OnClose
    public void end() {
        connections.remove(this);
        String message = String.format("* %s %s", userId, "has disconnected");
        LOG.info(message);
    }

    @OnMessage
    public void incoming(String message) {
        JSONObject object = JSON.parseObject(message);
        if(StringUtils.isNotBlank(message)){
            Integer requestCode = object.getInteger("requestCode");
            OrderInfo orderInfo = new OrderInfo();
            Long dishId = object.getLong("dishId");
            Long shopId = object.getLong("shopId");
            orderInfo.setShopId(shopId);
            orderInfo.setUserId(userId);
            switch(requestCode){
                case 101:
                    sendOrder(orderInfo);
                    break;
                case 102:
                    cancelOrder(orderInfo);
                    break;
                case 103:
                    repast(orderInfo);
                    break;
                case 104:
                    orderOffline(orderInfo);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 错误是触发
     * @param h
     */
    @OnError
    public void onError(Throwable h) {
        LOG.error("Send Error: " + h.toString(), h);
    }

    /**
     * 发送订单
     * @param orderInfo
     */
    public  void sendOrder(OrderInfo orderInfo) {
        orderInfo.setOrderType(6);
        orderInfo.setOrderState(OrderState.WAITING.getValue());
        OrderInfo orderResult = orderService.createOrder(orderInfo);
        try {
            OrderSender userC = (OrderSender) connections.get(String.valueOf(userId));
            userC.session.getBasicRemote().sendText(JSON.toJSONString(ResponseData.successData(orderResult)));
            OrderSender serverC = (OrderSender) connections.get(orderInfo.getShopId());
            serverC.session.getBasicRemote().sendText(JSON.toJSONString(ResponseData.successData(orderResult)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 取消订单
     * @param orderInfo
     */
    public void cancelOrder(OrderInfo orderInfo){
        orderInfo.setOrderState(OrderState.CANCEL.getValue());
        int result = orderService.updateOrderState(orderInfo);
        sendMessageToUser(result);
        sendMessageToServer(result);
    }

    /**
     * 传菜
     * @param orderInfo
     */
    public void repast(OrderInfo orderInfo){
        orderInfo.setOrderState(OrderState.REPASTING.getValue());
        int result = orderService.updateOrderState(orderInfo);
        sendMessageToUser(result);
        sendMessageToServer(result);
    }

    /**
     * 线下点单
     * @param orderInfo
     */
    public void orderOffline(OrderInfo orderInfo){
        orderInfo.setOrderType(7);
        orderInfo.setOrderState(OrderState.WAITING.getValue());
        int result = orderService.updateOrderState(orderInfo);
        sendMessageToServer(result);
    }

    /**
     * 发送给用户
     * @param message
     */
    public static void sendMessageToUser(Object message) {
        OrderSender userC = (OrderSender) connections.get(String.valueOf(userId));
            userC.session.getAsyncRemote().sendObject(ResponseData.successData(message));
    }

    /**
     * 发送给点单员
     * @param message
     */
    public static void sendMessageToServer(Object message) {
        OrderSender serverC = (OrderSender) connections.get("server");
            serverC.session.getAsyncRemote().sendObject(ResponseData.successData(message));
    }
}

