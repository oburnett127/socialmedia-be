package com.oburnett127.socialmedia.websocket;
// package com.oburnett127.socialmedia.messaging;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.apache.logging.log4j.LogManager;
// import org.apache.logging.log4j.Logger;
// import org.springframework.amqp.rabbit.connection.ConnectionFactory;
// import org.springframework.amqp.rabbit.core.RabbitAdmin;
// import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;

//use this with the new websocket implementation
// @Configuration
// public class RabbitConfiguration {
//     private static final Logger logger = LogManager.getLogger(RabbitConfiguration.class);

//     @Bean
//     MessageListenerAdapter listenerAdapter(CustomMessageListener listener) {
//         logger.debug("RabbitConfiguration.listenerAdapter() called.");
//         System.out.println("RabbitConfiguration.listenerAdapter() called.");
//         return new MessageListenerAdapter(listener, "onMessage");
//     }

//     @Bean
//     public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
//         logger.debug("RabbitConfiguration.rabbitAdmin() called.");
//         System.out.println("RabbitConfiguration.rabbitAdmin() called.");
//         return new RabbitAdmin(connectionFactory);
//     }
// }
