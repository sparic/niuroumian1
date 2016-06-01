package com.myee.niuroumian.websocket;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.myee.niuroumian.domain.OrderInfo;
import com.myee.niuroumian.filter.HTMLFilter;
import com.myee.niuroumian.response.ResponseData;
import com.myee.niuroumian.service.order.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * websocket 推送订单信息
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
            OrderInfo orderInfo = new OrderInfo();
            Long dishId = object.getLong("dishId");
            orderInfo.setDishId(dishId);
            Long shopId = object.getLong("shopId");
            orderInfo.setShopId(shopId);
            orderInfo.setUserId(userId);
            OrderInfo orderResult = orderService.createOrder(orderInfo);
            broadcast(orderResult);
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

    public static void broadcast(Object message) {
        sendMessageToUser(message);
        sendMessageToServer(message);
    }

    /**
     * 发送给用户
     * @param message
     */
    public static void sendMessageToUser(Object message) {
        OrderSender userC = (OrderSender) connections.get(String.valueOf(userId));
        try {
            userC.session.getBasicRemote().sendObject(ResponseData.successData(message));
        } catch (IOException e) {
            LOG.debug("Chat Error: Failed to send message to user", e);
            connections.remove(userC);
            try {
                userC.session.close();
            } catch (IOException e1) {
                LOG.error(userC.userId+ "has been disconnected.",e);
            }
        } catch (EncodeException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送给点单员
     * @param message
     */
    public static void sendMessageToServer(Object message) {
        OrderSender serverC = (OrderSender) connections.get("server");
        try {
            serverC.session.getBasicRemote().sendObject(ResponseData.successData(message));
        } catch (IOException e) {
            LOG.debug("Chat Error: Failed to send message to server", e);
            connections.remove(serverC);
            try {
                serverC.session.close();
            } catch (IOException e1) {
                LOG.error("server has been disconnected.",e);
            }
        } catch (EncodeException e) {
            e.printStackTrace();
        }
    }
}

