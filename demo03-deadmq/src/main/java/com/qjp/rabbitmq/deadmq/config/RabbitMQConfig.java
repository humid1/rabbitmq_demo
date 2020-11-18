package com.qjp.rabbitmq.deadmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qiujianping
 * @date Created in 2020/11/18 16:35
 */
@Configuration
public class RabbitMQConfig {
    /**
     * 定义延时交换机
     */
    public static final String DELAY_EXCHANGE_NAME = "delay.queue.demo.business.exchange";
    public static final String DEAD_LETTER_EXCHANGE = "delay.queue.demo.deadletter.exchange";

    /**
     * 定义延时队列
     */
    public static final String DELAY_QUEUEA_NAME = "delay.queue.demo.business.queuea";
    public static final String DELAY_QUEUEB_NAME = "delay.queue.demo.business.queueb";
    public static final String DEAD_LETTER_QUEUEA_ROUTING_KEY = "delay.queue.demo.deadletter.delay_10s.routingkey";
    public static final String DEAD_LETTER_QUEUEB_ROUTING_KEY = "delay.queue.demo.deadletter.delay_60s.routingkey";

    /**
     * 定义延时路由
     */
    public static final String DELAY_QUEUEA_ROUTING_KEY = "delay.queue.demo.business.queuea.routingkey";
    public static final String DELAY_QUEUEB_ROUTING_KEY = "delay.queue.demo.business.queueb.routingkey";
    public static final String DEAD_LETTER_QUEUEA_NAME = "delay.queue.demo.deadletter.queuea";
    public static final String DEAD_LETTER_QUEUEB_NAME = "delay.queue.demo.deadletter.queueb";

    /**
     * 声明延时 exchange
     */
    @Bean("delayExchange")
    public DirectExchange delayExchange() {
        return new DirectExchange(DELAY_EXCHANGE_NAME);
    }

    /**
     * 声明死信Exchange
     */
    @Bean("deadLetterExchange")
    public DirectExchange deadLetterExchange() {
        return new DirectExchange(DEAD_LETTER_EXCHANGE);
    }

    /**
     * 声明延时队列A，延时10s
     * 并绑定到对应的死信交换机
     */
    @Bean("delayQueueA")
    public Queue delayQueueA() {
        Map<String, Object> map = new HashMap<>(2);
        // x-dead-letter-exchange    这里声明当前队列绑定的死信交换机
        map.put("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE);
        // x-dead-letter-routing-key  这里声明当前队列的死信路由key
        map.put("x-dead-letter-routing-key", DEAD_LETTER_QUEUEA_ROUTING_KEY);
        // x-message-ttl  声明队列的TTL 生存时间
        map.put("x-message-ttl", 10 * 1000);
        return QueueBuilder.durable(DELAY_QUEUEA_NAME).withArguments(map).build();
    }

    /**
     * 声明延时队列A，延时60s
     * 并绑定到对应的死信交换机
     */
    @Bean("delayQueueB")
    public Queue delayQueueB() {
        Map<String, Object> map = new HashMap<>(2);
        map.put("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE);
        map.put("x-dead-letter-routing-key", DEAD_LETTER_QUEUEB_ROUTING_KEY);
        map.put("x-message-ttl", 60 * 1000);
        return QueueBuilder.durable(DELAY_QUEUEB_NAME).withArguments(map).build();
    }

    /**
     * 声明死信队列A 用于接收延时10s处理的消息
     */
    @Bean("deadLetterQueueA")
    public Queue deadLetterQueueA() {
        return new Queue(DEAD_LETTER_QUEUEA_NAME);
    }

    /**
     * 声明死信队列B 用于接收延时60s处理的消息
     */
    @Bean("deadLetterQueueB")
    public Queue deadLetterQueueB() {
        return new Queue(DEAD_LETTER_QUEUEB_NAME);
    }

    /**
     * 声明延时队列A绑定关系
     */
    @Bean
    public Binding delayBindA(@Qualifier("delayQueueA") Queue queue,
                              @Qualifier("delayExchange") DirectExchange directExchange) {
        return BindingBuilder.bind(queue).to(directExchange).with(DELAY_QUEUEA_ROUTING_KEY);
    }

    /**
     * 声明延时队列B绑定关系
     */
    @Bean
    public Binding delayBindB(@Qualifier("delayQueueB") Queue queue,
                              @Qualifier("delayExchange") DirectExchange directExchange) {
        return BindingBuilder.bind(queue).to(directExchange).with(DELAY_QUEUEB_ROUTING_KEY);
    }

    /**
     * 声明死信队列A绑定关系
     */
    @Bean
    public Binding deadLetterBindA(@Qualifier("deadLetterQueueA") Queue queue,
                              @Qualifier("deadLetterExchange") DirectExchange directExchange) {
        return BindingBuilder.bind(queue).to(directExchange).with(DEAD_LETTER_QUEUEA_ROUTING_KEY);
    }

    /**
     * 声明死信队列B绑定关系
     */
    @Bean
    public Binding deadLetterBindB(@Qualifier("deadLetterQueueB") Queue queue,
                                   @Qualifier("deadLetterExchange") DirectExchange directExchange) {
        return BindingBuilder.bind(queue).to(directExchange).with(DEAD_LETTER_QUEUEB_ROUTING_KEY);
    }
}
