// package com.oburnett127.socialmedia.service;

// import org.springframework.amqp.core.Queue;
// import org.springframework.amqp.rabbit.connection.ConnectionFactory;
// import org.springframework.amqp.rabbit.core.RabbitAdmin;
// import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
// import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import com.oburnett127.socialmedia.messaging.CustomMessageListener;

//use this with the new websocket implementation
// @Service
// public class RabbitService {
    
//     @Autowired
//     private RabbitAdmin rabbitAdmin;

//     @Autowired
//     private ConnectionFactory connectionFactory;

//     public void registerQueue(String queueName) {
//         Queue queue = new Queue(queueName, false);
//         rabbitAdmin.declareQueue(queue);

//         SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//         container.setConnectionFactory(connectionFactory);
//         container.setQueueNames(queueName);
//         container.setMessageListener(new MessageListenerAdapter(new CustomMessageListener()));
//         container.start();
//     }
// }
