package com.oburnett127.socialmedia.messaging;

import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class MessageConsumerService {

    private final CopyOnWriteArrayList<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    @Autowired
    private ConnectionFactory connectionFactory;

    @Autowired
    private CustomMessageListener customMessageListener;

    public void handleMessage(String message) {
        System.out.println("Received message: " + message);

        for (WebSocketSession session : sessions) {
            try {
                session.sendMessage(new TextMessage(message));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void startListeningToQueue(String queueName) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(customMessageListener);
        container.start();
    }

    public CopyOnWriteArrayList<WebSocketSession> getSessions() {
        return sessions;
    }
}
