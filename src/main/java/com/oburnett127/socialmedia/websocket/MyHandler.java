// package com.oburnett127.socialmedia.websocket;

// import java.io.IOException;
// import java.util.HashSet;
// import java.util.Set;
// import org.apache.logging.log4j.LogManager;
// import org.apache.logging.log4j.Logger;
// import org.springframework.web.socket.CloseStatus;
// import org.springframework.web.socket.TextMessage;
// import org.springframework.web.socket.WebSocketSession;
// import org.springframework.web.socket.handler.TextWebSocketHandler;

// use this for the new websocket implementation
// public class MyHandler extends TextWebSocketHandler {
//     private Set<WebSocketSession> sessions = new HashSet<>();
//     private static final Logger logger = LogManager.getLogger(MyHandler.class);

//     @Override
//     public void handleTextMessage(WebSocketSession session, TextMessage message) {
//         // Handle received messages here
//         logger.debug("MyHandler.handleTextMessage() called.");
//         System.out.println("MyHandler.handleTextMessage() called.");
//     }

//     @Override
//     public void afterConnectionEstablished(WebSocketSession session) {
//         logger.debug("MyHandler.afterConnectionEsablished() called.");
//         System.out.println("MyHandler.afterConnectionEsablished() called.");
//         sessions.add(session);
//     }

//     @Override
//     public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
//         logger.debug("MyHandler.afterConnectionClosed() called.");
//         System.out.println("MyHandler.afterConnectionClosed() called.");
//         sessions.remove(session);
//     }

//     public void sendMessageToAll(String message) {
//         logger.debug("MyHandler.sendMessageToAll() called. Message: " + message);
//         System.out.println("MyHandler.sendMessageToAll() called. Message: " + message);

//         for (WebSocketSession session : sessions) {
//             try {
//                 session.sendMessage(new TextMessage(message));
//             } catch(IOException e) {
//                 e.printStackTrace();
//             } catch(Exception e) {
//                 e.printStackTrace();
//             }
//         }
//     }
// }
