package com.fisæ.services.vote.infrastructure.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring AMQP configuration class
 */
@Configuration
public class AmqpConfiguration {

    /**
     * Whether or not the queue will be kept when the broker will be shut down
     */
    @Value("${amqp.queue.durable}")
    public boolean isDurable;

    /**
     * Queue name
     */
    @Value("${amqp.queue.name}")
    public String queueName;

    /**
     * Create the AMQP queue
     *
     * @return The created queue
     */
    @Bean
    public Queue queue() {
        return new Queue(queueName, isDurable);
    }

    /**
     * Create the default RabbitMQ listener factory, configured to handle JSON
     *
     * @return The RabbitMQ listener factory
     */
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());

        return factory;
    }

}
