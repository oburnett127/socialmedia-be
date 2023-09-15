// package com.oburnett127.socialmedia.websocket;

// import org.apache.logging.log4j.LogManager;
// import org.apache.logging.log4j.Logger;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.socket.WebSocketHandler;
// import org.springframework.web.socket.config.annotation.EnableWebSocket;
// import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
// import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

// use this for the new websocket implementation
// @Configuration
// @EnableWebSocket
// public class WebSocketConfig implements WebSocketConfigurer {
//     private static final Logger logger = LogManager.getLogger(WebSocketConfig.class);

//     @Override
//     public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//         logger.debug("WebSocketConfig.registerWebSocketHandlers() called.");
//         System.out.println("WebSocketConfig.registerWebSocketHandlers() called.");
//         registry.addHandler(myHandler(), "/myHandler").setAllowedOrigins("http://localhost:3000");
//     }

//     @Bean
//     public WebSocketHandler myHandler() {
//         logger.debug("WebSocketConfig.myHandler() called.");
//         System.out.println("WebSocketConfig.myHandler() called.");
//         return (WebSocketHandler) new MyHandler();
//     }
// }
