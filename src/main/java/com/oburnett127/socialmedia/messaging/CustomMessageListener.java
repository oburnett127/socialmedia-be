package com.oburnett127.socialmedia.messaging;

import java.io.IOException;
import java.util.List;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

@Service
public class CustomMessageListener implements MessageListener {

    @Autowired
    private ApplicationContext applicationContext;

    private List<WebSocketSession> getSessions() {
        return applicationContext.getBean("webSocketSessions", List.class);
    }

    @Override
    public void onMessage(Message message) {
        List<WebSocketSession> sessions = getSessions();
        String payload = new String(message.getBody());

        for (WebSocketSession session : sessions) {
            try {
                session.sendMessage(new TextMessage(payload));
            } catch (IOException e) {
                e.printStackTrace();
                //May want to remove the session from the list
            }
        }

        System.out.println("Received message: " + payload);
    }
}
