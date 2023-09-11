package com.oburnett127.socialmedia.messaging;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;

@Configuration
public class RabbitConfiguration {
    @Bean
    MessageListenerAdapter listenerAdapter(CustomMessageListener listener) {
        return new MessageListenerAdapter(listener, "onMessage");
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }
}
