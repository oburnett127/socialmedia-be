package com.oburnett127.socialmedia;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.TopicExchange;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@SpringBootApplication
@EnableJpaRepositories
@EnableRabbit
public class SocialmediaApplication {

    public static final String EXCHANGE_NAME = "post_exchange";
    public static final String ROUTING_KEY_PREFIX = "user.";

    public static void main(String[] args) {
        SpringApplication.run(SocialmediaApplication.class, args);
    }

    @Bean
    public TopicExchange declareExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }
}
