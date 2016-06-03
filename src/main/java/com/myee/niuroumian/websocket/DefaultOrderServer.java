package com.myee.niuroumian.websocket;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Maps;
import com.myee.niuroumian.common.Constants;
import com.myee.niuroumian.common.NoticeHandler;
import com.myee.niuroumian.dao.OrderInfoDao;
import com.myee.niuroumian.domain.OrderInfo;
import com.myee.niuroumian.response.Headers;
import com.myee.niuroumian.response.Message;
import com.myee.niuroumian.response.MessageType;
import com.myee.niuroumian.response.Response;
import com.myee.niuroumian.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by Jelynn on 2016/6/2.
 */
public class DefaultOrderServer extends AbstractWebSocketHandler implements OrderServer {

    @Autowired
    private OrderInfoDao orderInfoDao;

    private ConcurrentMap<String, WebSocketSession> eptSessions = Maps.newConcurrentMap();

    //用缓存代替map存储在线设备状态信息，缓存定时可以销毁
    private LoadingCache<String,String> loadingCacheSessions = CacheBuilder
            .newBuilder()
            .expireAfterAccess(Constants.EXPIRE_AFTERACCESS_TIME, TimeUnit.SECONDS)
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String key) throws Exception {
                    return "####keyIs:" + key;
                }
            });

    private ConcurrentMap<String, String> sessionKeys = Maps.newConcurrentMap();

    private static ThreadLocal<StringBuffer> msgTheadLocal = new ThreadLocal<StringBuffer>();

    public Response<?> handleMessage(Message message, WebSocketSession session) {
        Response<?> outMessage = null;
        if (message.is(MessageType.REGISTER)) {
            outMessage = handleRegister(session, message);
        } else if (message.is(MessageType.PING)) {
            outMessage = handleHeartbeat(message);
        }
        return outMessage;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage text) throws Exception {
        try {
            Message inMessage = Message.parse(text.getPayload());
            Response<?> outMessage = handleMessage(inMessage, session);
            if (null != outMessage) {
                sendMessage(session, outMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        try {
            String sessionId = session.getId();
            String key = sessionKeys.get(sessionId);
            if (null != key && eptSessions.containsKey(key)) {
                eptSessions.remove(key);
                sessionKeys.remove(sessionId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //注册只有连接时才能写入session和Cache
    public Response<?> handleRegister(WebSocketSession session, final Message register) {
        Headers headers = register.headerWapper();
        String boardNo = headers.getBoardNumber();
        eptSessions.put(boardNo, session);
        sessionKeys.put(session.getId(), boardNo);

        //........更新Cache
        messageLoadToCache(register);
//        System.out.println("#########注册在线设备："+boardNo+","+new Date());
        //写入上线日志
//        saveProductStatusLog(session, register, Constants.PRODUCT_STATUS_TYPE_ONLINE);//注册时间类型为上线时间

        return Response.successResult();
    }

    //心跳时更新Cache...
    public Response<?> handleHeartbeat(final Message heartbeat) {
//        System.out.println("#########心跳在线设备："+heartbeat.headerWapper().getBoardNumber()+","+new Date());
        messageLoadToCache(heartbeat);
        return new Response<>(new Date());
    }

    //把消息装载到cache
    public void messageLoadToCache(final Message message){
        try{
            if(message != null) {
                //......更新有同步心跳设备的Cache
                Map<String, Object> header = message.getHeaders();
                String boardNo = (String)header.get("Board-Number");
                String messageBody = StringUtil.nullToString(message.getBody());
                loadingCacheSessions.put(boardNo,messageBody);
            }
        }catch (Exception e){
//            e.printStackTrace();//暂时不打印，调试时太乱
        }
    }

    void sendMessage(WebSocketSession wss, Message message) throws IOException {
        String rawString = Message.toRawString(message);
        TextMessage txtMessge = new TextMessage(rawString);
        wss.sendMessage(txtMessge);
    }

    public boolean supportsPartialMessages() {
        return false;
    }

    public LoadingCache<String, String> getLoadingCacheSessions() {
        return loadingCacheSessions;
    }

    @Transactional
    public OrderInfo createOrder(OrderInfo orderInfo){
        return orderInfoDao.save(orderInfo);
    }

    @Transactional
    public int updateOrderState(OrderInfo orderInfo){
        return orderInfoDao.updateOrderState(orderInfo.getUserId(), orderInfo.getOrderId(), orderInfo.getOrderState());
    }
}
