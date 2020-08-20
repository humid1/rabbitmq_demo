package com.qjp.rabbitmq.study.config;

import com.qjp.rabbitmq.study.constant.RabbitConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author qiujianping
 * @date Created in 2020/8/20 17:10
 * header_exchange 设置头部信息进行匹配（全部匹配和部分匹配）
 */
@Configuration
public class HeadersRabbitConfig implements BeanPostProcessor {
    @Resource
    private RabbitAdmin rabbitAdmin;

    @Bean
    public HeadersExchange rabbitHeadersExchangeDemo() {
        // 1.交换机名称 2.是否持久化  3.是否自动删除，也就是临时队列。当最后一个消费者断开连接后，会自动删除
        return new HeadersExchange(RabbitConstant.HEADERS_EXCHANGE_DEMO, true, false);
    }

    @Bean
    public Queue headersQueueA() {
        /**
         * 1. name: 队列名称
         * 2. durable: 是否持久化
         * 3. exclusive: 是否独享、排外。如果设置为 true，定义为排他队列，则只有创建者可以使用此队列
         * 4. autoDelete：是否自动删除，也就是临时队列。当最后一个消费者断开连接后，会自动删除
         */
        return new Queue(RabbitConstant.HEADERS_EXCHANGE_QUEUE_A, true, false, false);
    }

    @Bean
    public Queue headersQueueB() {
        return new Queue(RabbitConstant.HEADERS_EXCHANGE_QUEUE_B, true, false, false);
    }

    @Bean
    public Binding bindHeadersA() {
        Map<String, Object> map = new HashMap<>();
        map.put("key_one", "java");
        map.put("key_two", "rabbit");
        // 全匹配
        return BindingBuilder.bind(headersQueueA())
                .to(rabbitHeadersExchangeDemo())
                .whereAll(map)
                .match();
    }

    @Bean
    public Binding bindHeadersB() {
        Map<String, Object> map = new HashMap<>();
        map.put("headers_A", "code");
        map.put("headers_B", "sky");
        // 全匹配
        return BindingBuilder.bind(headersQueueB())
                .to(rabbitHeadersExchangeDemo())
                .whereAny(map)
                .match();
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        rabbitAdmin.declareExchange(rabbitHeadersExchangeDemo());
        rabbitAdmin.declareQueue(headersQueueA());
        rabbitAdmin.declareQueue(headersQueueB());
        return null;
    }
}
