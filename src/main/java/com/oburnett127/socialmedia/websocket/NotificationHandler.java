package com.oburnett127.socialmedia.websocket;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.oburnett127.socialmedia.messaging.MessageConsumerService;

public class NotificationHandler extends TextWebSocketHandler {

    private MessageConsumerService messageConsumerService;

    public NotificationHandler(MessageConsumerService messageConsumerService) {
        this.messageConsumerService = messageConsumerService;
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        //used for messages from the client, not expecting messages from the client
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        messageConsumerService.getSessions().add(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        messageConsumerService.getSessions().remove(session);
    }
}
