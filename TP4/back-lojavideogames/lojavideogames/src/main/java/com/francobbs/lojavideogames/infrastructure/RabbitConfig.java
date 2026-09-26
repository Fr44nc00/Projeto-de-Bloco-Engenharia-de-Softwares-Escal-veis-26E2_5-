package com.francobbs.lojavideogames.infrastructure;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String QUEUE_NAME = "compraQueue";
    public static final String EXCHANGE_NAME = "compraExchange";
    public static final String ROUTING_KEY = "compraRoutingKey";

    public static final String DLQ_NAME = "compraDLQ";
    public static final String DLX_NAME = "dlxExchange";
    public static final String DLQ_ROUTING_KEY = "dlqRoutingKey";

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin admin = new RabbitAdmin(connectionFactory);
        admin.setAutoStartup(true);
        return admin;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

    @Bean
    public Queue compraQueue() {
        return QueueBuilder.durable(QUEUE_NAME)
                .withArgument("x-dead-letter-exchange", DLX_NAME)
                .withArgument("x-dead-letter-routing-key", DLQ_ROUTING_KEY)
                .build();
    }

    @Bean
    public TopicExchange compraExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding binding(Queue compraQueue, TopicExchange compraExchange) {
        return BindingBuilder.bind(compraQueue).to(compraExchange).with(ROUTING_KEY);
    }

    @Bean
    public TopicExchange dlxExchange() {
        return new TopicExchange(DLX_NAME);
    }

    @Bean
    public Queue dlq() {
        return new Queue(DLQ_NAME, true);
    }

    @Bean
    public Binding dlqBinding(Queue dlq, TopicExchange dlxExchange) {
        return BindingBuilder.bind(dlq).to(dlxExchange).with(DLQ_ROUTING_KEY);
    }
}
