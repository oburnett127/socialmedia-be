package com.oburnett127.socialmedia.websocket;
// package com.oburnett127.socialmedia.messaging;

// import org.apache.logging.log4j.LogManager;
// import org.apache.logging.log4j.Logger;
// import org.springframework.amqp.rabbit.connection.ConnectionFactory;
// import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import org.springframework.web.socket.TextMessage;
// import org.springframework.web.socket.WebSocketSession;

// import java.util.concurrent.CopyOnWriteArrayList;

// use this for the new websocket implementation
// @Service
// public class MessageConsumerService {

//     private final CopyOnWriteArrayList<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
//     private static final Logger logger = LogManager.getLogger(MessageConsumerService.class);

//     @Autowired
//     private ConnectionFactory connectionFactory;

//     @Autowired
//     private CustomMessageListener customMessageListener;

//     public void handleMessage(String message) {
//         logger.debug("MessageConsumerService.handleMessage() called. Received message: " + message);
//         System.out.println("MessageConsumerService.handleMessage() called. Received message: " + message);
//         System.out.println("Received message: " + message);

//         for (WebSocketSession session : sessions) {
//             try {
//                 session.sendMessage(new TextMessage(message));
//             } catch (Exception e) {
//                 e.printStackTrace();
//             }
//         }
//     }

//     public void startListeningToQueue(String queueName) {
//         logger.debug("CustomMessageListener.startListeningToQueue() called.");
//         System.out.println("CustomMessageListener.startListeningToQueue() called.");
//         try {
//             SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//             container.setConnectionFactory(connectionFactory);
//             container.setQueueNames(queueName);
//             container.setMessageListener(customMessageListener);
//             container.start();
//         } catch(Exception e) {
//             e.printStackTrace();
//         }
//     }

//     public CopyOnWriteArrayList<WebSocketSession> getSessions() {
//         logger.debug("CustomMessageListener.getSessions() called.");
//         System.out.println("CustomMessageListener.getSessions() called.");
//         return sessions;
//     }
// }
