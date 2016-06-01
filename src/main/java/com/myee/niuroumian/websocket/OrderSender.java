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
 * websocket ���Ͷ�����Ϣ
 * Created by Jelynn on 2016/5/31.
 * //ע��˷��ʵ�ַ��ʽ��:
 * "ws://"+ window.location.host+"/${pageContext.request.contextPath}/test/chat"��ws��ͷ��,��������http:��ͷ��.
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
     * ������ʱ����
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
     * �ر�����ʱ����
     */
    @OnClose
    public void end() {
        connections.remove(this);
        String message = String.format("* %s %s", nickname, "has disconnected");
        broadcast(message);
    }

    /**
     * ��Ϣ���ʹ�������
     * @param message
     */
    /**
     * ��Ϣ���ʹ�������
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
     * �쳣ʱ����
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
     * ��ָ���û�������Ϣ
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
     * �������û�����
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

