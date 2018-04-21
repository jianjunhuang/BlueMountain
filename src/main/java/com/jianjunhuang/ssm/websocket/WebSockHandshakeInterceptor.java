package com.jianjunhuang.ssm.websocket;

import org.springframework.http.HttpHeaders;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

public class WebSockHandshakeInterceptor implements HandshakeInterceptor {
    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse,
                                   WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        System.out.println(serverHttpRequest.getHeaders());
        HttpHeaders httpHeaders = serverHttpRequest.getHeaders();

        if (!httpHeaders.containsKey("type")) {
            return false;
        }
        String type = httpHeaders.get("type").get(0);
        if (null == type || "".equals(type)) {
            return false;
        }

        map.put("type", type);

        if (type.equals("machine")) {
            if (httpHeaders.containsKey("machineId")) {
                map.put("machineId", httpHeaders.get("machineId").get(0));
                return true;
            }
        } else if (type.equals("phone")) {
            if (httpHeaders.containsKey("userId") && httpHeaders.containsKey("machineId")) {
                map.put("userId", httpHeaders.get("userId").get(0));
                map.put("machineId", httpHeaders.get("machineId").get(0));
                return true;
            }
        }
        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
        System.out.println("after handshake");
    }
}
