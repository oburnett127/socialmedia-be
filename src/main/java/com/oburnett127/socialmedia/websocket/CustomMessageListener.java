package com.oburnett127.socialmedia.websocket;
// package com.oburnett127.socialmedia.messaging;

// import java.io.IOException;
// import java.util.List;
// import org.springframework.amqp.core.Message;
// import org.springframework.amqp.core.MessageListener;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.ApplicationContext;
// import org.springframework.stereotype.Service;
// import org.springframework.web.socket.TextMessage;
// import org.springframework.web.socket.WebSocketSession;
// import org.apache.logging.log4j.LogManager;
// import org.apache.logging.log4j.Logger;

// use this for the new websocket implementation
// @Service
// public class CustomMessageListener implements MessageListener {

//     @Autowired
//     private ApplicationContext applicationContext;
//     private static final Logger logger = LogManager.getLogger(CustomMessageListener.class);

//     private List<WebSocketSession> getSessions() {
//         logger.debug("CustomMessageListener.getSessions() called.");
//         System.out.println("CustomMessageListener.getSessions() called.");
//         return applicationContext.getBean("webSocketSessions", List.class);
//     }

//     @Override
//     public void onMessage(Message message) {
//         List<WebSocketSession> sessions = getSessions();
//         String payload = new String(message.getBody());

//         logger.debug("CustomMessageListener.onMessage() called. Received message: " + message);
//         System.out.println("CustomMessageListener.onMessage() called. Received message: " + message);

//         for (WebSocketSession session : sessions) {
//             try {
//                 session.sendMessage(new TextMessage(payload));
//             } catch (IOException e) {
//                 e.printStackTrace();
//             }
//         }

//         System.out.println("Received message: " + payload);
//     }
// }
