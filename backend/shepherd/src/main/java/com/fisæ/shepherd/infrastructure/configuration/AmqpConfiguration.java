package com.fisæ.shepherd.infrastructure.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
     * Create a RabbitTemplate bound to the named queue defined in the properties
     *
     * @param connectionFactory The RabbitMQ connection factory used to set up the queue
     *
     * @return The configured RabbitTemplate
     */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setRoutingKey(queueName);

        return template;
    }

}
