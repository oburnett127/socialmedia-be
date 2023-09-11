package com.oburnett127.socialmedia.websocket;

import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.oburnett127.socialmedia.messaging.MessageConsumerService;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private MessageConsumerService messageConsumerService;

    public WebSocketConfig(MessageConsumerService messageConsumerService) {
        this.messageConsumerService = messageConsumerService;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new NotificationHandler(messageConsumerService), "/notifications")
                .setAllowedOrigins("*");
    }

    @Bean
    public CopyOnWriteArrayList<WebSocketSession> webSocketSessions() {
        return new CopyOnWriteArrayList<>();
    }
}

