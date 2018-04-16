package com.jianjunhuang.ssm.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
public class WebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        //1.注册WebSocket
        String websocket_url = "/websocket/socketServer.do";                        //设置websocket的地址
        registry.addHandler(new WebSocketHandler(), websocket_url).                          //注册Handler
                addInterceptors(new WebSockHandshakeInterceptor());                   //注册Interceptor

        //2.注册SockJS，提供SockJS支持(主要是兼容ie8)
        String sockjs_url = "/sockjs/socketServer.do";                              //设置sockjs的地址
        registry.addHandler(new WebSocketHandler(), sockjs_url).                            //注册Handler
                addInterceptors(new WebSockHandshakeInterceptor()).                   //注册Interceptor
                withSockJS();                                                           //支持sockjs协议
    }
}
