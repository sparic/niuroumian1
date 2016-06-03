//package com.myee.niuroumian.config;
//
//import com.myee.niuroumian.controller.OrderSender;
//import com.myee.niuroumian.websocket.OrderServer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.converter.MessageConverter;
//import org.springframework.messaging.simp.config.MessageBrokerRegistry;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.socket.config.annotation.*;
//import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;
//
//import java.util.List;
//
//@Configuration
//@EnableTransactionManagement
//@EnableWebMvc
//@EnableWebSocket
//@EnableScheduling
//@EnableWebSocketMessageBroker
//public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer implements WebSocketConfigurer {
//
//    @Autowired
//    private OrderServer orderServer;
//
//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry config) {
//        config.enableSimpleBroker("/topic/", "/queue/");
//        config.setApplicationDestinationPrefixes("/app");
//    }
//
//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        registry.addEndpoint("/portfolio").withSockJS();
//    }
//
//    @Override
//    public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
//        return true;
//    }
//
//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        registry.addHandler(orderServer, "/niuroumian");
////        registry.addHandler(orderSender, "/niuroumian").withSockJS();
//    }
//
//
//    @Bean
//    public ServletServerContainerFactoryBean createWebSocketContainer() {
//        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
//        container.setMaxTextMessageBufferSize(100 * 1024);
//        container.setMaxBinaryMessageBufferSize(8192);
//        return container;
//    }
//
//    @Override
//    public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
//    }
//
//}