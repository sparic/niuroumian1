package com.myee.niuroumian.websocket;


import com.myee.niuroumian.filter.HTMLFilter;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

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
@ServerEndpoint("/niuroumian/sendorder")
public class OrderSender {

    private static final Log LOG = LogFactory.getLog(OrderSender.class);

    private static final String GUEST_PREFIX = "GUEST";
    private static final AtomicInteger connectionIds = new AtomicInteger(0);
    private static final Map<String, Object> connections = new HashMap<String, Object>();

    private final String nickname;
    private Session session;

    public OrderSender() {
        nickname = GUEST_PREFIX + connectionIds.getAndDecrement();
    }

    /**
     * 打开连接时触发
     *
     * @param session
     */
    @OnOpen
    public void start(@PathParam("session") Session session) {
        this.session = session;
        connections.put(nickname, this);
        String message = String.format("* %s %s", nickname, "has joined");
        LOG.info("Websocket Start Connecting:");
        broadcast(message);
    }

    /**
     * 关闭连接时触发
     */
    @OnClose
    public void end() {
        connections.remove(this);
        String message = String.format("* %s %s", nickname, "has disconnected");
        broadcast(message);
    }

    /**
     * 消息发送触发方法
     * @param message
     */
    /**
     * 消息发送触发方法
     *
     * @param message
     */
    @OnMessage
    public void incoming(String message) {
        // Never trust the client
        String filteredMessage = String.format("%s: %s",
                nickname, HTMLFilter.filter(message.toString()));
        broadcast(filteredMessage);
    }

    /**
     * 异常时触发
     *
     * @param h
     */
    @OnError
    public void onError(@PathParam("h") Throwable h) {
        LOG.error("Send Error: " + h.toString(), h);
    }

    public static void broadcast(String message) {

        if (message.indexOf("Guest0") != -1) {
            sendUser(message);
        } else {
            sendAll(message);
        }
    }

    /**
     * 向指定用户发送消息
     *
     * @param message
     */
    public static void sendUser(String message) {
        OrderSender c = (OrderSender) connections.get("Guest0");
        try {
            c.session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            LOG.debug("Chat Error: Failed to send message to client", e);
            connections.remove(c);
            try {
                c.session.close();
            } catch (IOException e1) {
                // Ignore
            }
            String msg = String.format("* %s %s",
                    c.nickname, "has been disconnected.");
            broadcast(msg);
        }
    }


    /**
     * 向所有用户发送
     *
     * @param message
     */
    public static void sendAll(String message) {
        for (String key : connections.keySet()) {
            OrderSender client = null;
            try {
                client = (OrderSender) connections.get(key);
                synchronized (client) {
                    client.session.getBasicRemote().sendText(message);
                }
            } catch (IOException e) {
                LOG.debug("Chat Error: Failed to send message to client", e);
                connections.remove(client);
                try {
                    client.session.close();
                } catch (IOException e1) {
                    // Ignore
                }
                String msg = String.format("* %s %s",
                        client.nickname, "has been disconnected.");
                broadcast(msg);
            }
        }
    }
}

