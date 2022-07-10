package com.example.rabbitmqspringdemo.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 *  new merchant pass -> new merchant queue -> dead letter message exchange -> queue
 */
@Configuration
public class RabbitMQConfig {

    /**
     * dead letter queue
     */
    public static final String LOCK_MERCHANT_DEAD_QUEUE = "lock_merchant_dead_queue";

    /**
     * dead letter exchange
     */
    public static final String LOCK_MERCHANT_DEAD_EXCHANGE = "lock_merchant_dead_exchange";

    /**
     * enter dead letter routing key
     */
    public static final String LOCK_MERCHANT_ROUTING_KEY = "lock_merchant_routing_key";

    /**
     * create dead letter exchange
     * @return
     */
    @Bean
    public Exchange lockMerchantExchange() {
        return new TopicExchange(LOCK_MERCHANT_DEAD_EXCHANGE, true, false);
    }

    /**
     * create dead letter queue
     * @return
     */
    @Bean
    public Queue lockMerchantQueue() {
        return QueueBuilder.durable(LOCK_MERCHANT_DEAD_QUEUE).build();
    }

    /**
     * bind exchange and queue
     * @return
     */
    @Bean
    public Binding lockMerchantBinding() {
        return new Binding(LOCK_MERCHANT_DEAD_QUEUE, Binding.DestinationType.QUEUE,
                LOCK_MERCHANT_DEAD_EXCHANGE, LOCK_MERCHANT_ROUTING_KEY, null);
    }

    /**
     * normal queue, binding dead lock exchange
     */
    public static final String NEW_MERCHANT_QUEUE = "new_merchant_queue";

    /**
     * normal topic exchange
     */
    public static final String NEW_MERCHANT_EXCHANGE = "new_merchant_exchange";

    /**
     * routing key
     */
    public static final String NEW_MERCHANT_ROUTING_KEY = "new_merchant_routing_key";


    /**
     * create normal exchange
     * @return
     */
    @Bean
    public Exchange newMerchantExchange() {
        return new TopicExchange(NEW_MERCHANT_EXCHANGE, true, false);
    }

    /**
     * create normal queue
     * @return
     */
    @Bean
    public Queue newMerchantQueue() {
        Map<String, Object> args = new HashMap<>(3);

        // when message expire, enter dead letter exchange
        args.put("x-dead-letter-exchange", LOCK_MERCHANT_DEAD_EXCHANGE);

        // when message expire, the routing key to enter dead letter exchange
        args.put("x-dead-letter-routing-key", LOCK_MERCHANT_ROUTING_KEY);

        // expire time, in ms
        args.put("x-message-ttl", 10000);

        return QueueBuilder.durable(NEW_MERCHANT_QUEUE).withArguments(args).build();
    }

    /**
     * bind exchange and queue
     * @return
     */
    @Bean
    public Binding newMerchantBinding() {
        return new Binding(NEW_MERCHANT_QUEUE, Binding.DestinationType.QUEUE,
                NEW_MERCHANT_EXCHANGE, NEW_MERCHANT_ROUTING_KEY, null);
    }
//    public static final String EXCHANGE_NAME = "order_exchange";
//
//    public static final String QUEUE = "order_queue";
//
//    /**
//     * topic exchange build
//     * @return
//     */
//    @Bean
//    public Exchange orderExchange() {
//        return ExchangeBuilder.topicExchange(EXCHANGE_NAME).durable(true).build();
//    }
//
//    /**
//     * queue build
//     * @return
//     */
//    @Bean
//    public Queue orderQueue() {
//        return QueueBuilder.durable(QUEUE).build();
//    }
//
//    /**
//     * bind the queue with exchange
//     * @param queue
//     * @param exchange
//     * @return
//     */
//    @Bean
//    public Binding orderBinding(Queue queue, Exchange exchange) {
//        return BindingBuilder.bind(queue).to(exchange).with("order.#").noargs();
//    }
}
