package com.jianjunhuang.ssm.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private CoffeeWebSocketHandler handler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        //1.注册WebSocket
        String websocket_url = "/websocket/socketServer";                        //设置websocket的地址
        registry.addHandler(handler, websocket_url).                          //注册Handler
                addInterceptors(new WebSockHandshakeInterceptor());                   //注册Interceptor

        //2.注册SockJS，提供SockJS支持(主要是兼容ie8)
//        String sockjs_url = "/sockjs/socketServer.do";                              //设置sockjs的地址
//        registry.addHandler(new CoffeeWebSocketHandler(), sockjs_url).                            //注册Handler
//                addInterceptors(new WebSockHandshakeInterceptor()).                   //注册Interceptor
//                withSockJS();                                                           //支持sockjs协议
    }
}
