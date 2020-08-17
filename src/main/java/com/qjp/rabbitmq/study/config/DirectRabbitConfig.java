package com.qjp.rabbitmq.study.config;

import com.qjp.rabbitmq.study.constant.RabbitMQConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Author: qiujianping
 * @Date: Created in 2020/8/17 10:26
 * @Description: Direct交换机以及队列的配置类
 */
@Configuration
public class DirectRabbitConfig {
    @Bean
    public Queue rabbitmqDemoDirectQueue() {
        /**
         * 1. name: 队列名称
         * 2. durable: 是否持久化
         * 3. exclusive: 是否独享、排外。如果设置为 true，定义为排他队列，则只有创建者可以使用此队列
         * 4. autoDelete：是否自动删除，也就是临时队列。当最后一个消费者断开连接后，会自动删除
         */
        return new Queue(RabbitMQConstant.RABBITMQ_DEMO_TOPIC, true, false, false);
    }

    @Bean
    public DirectExchange rabbitmqDemoDirectExchange() {
        /**
         *  Direct 交换机
         *  1. name: 交换机名称
         *  2. durable: 是否持久化
         *  3. autoDelete: 是否自动删除
         */
        return new DirectExchange(RabbitMQConstant.RABBITMQ_DEMO_DIRECT_EXCHANGE, true ,false);
    }

    @Bean
    public Binding bindDirect() {
        // 链式写法，绑定队列和交换机，并设置匹配键
        return BindingBuilder
                // 绑定队列
                .bind(rabbitmqDemoDirectQueue())
                // 绑定交换机
                .to(rabbitmqDemoDirectExchange())
                // 设置配置键
                .with(RabbitMQConstant.RABBITMQ_DEMO_DIRECT_ROUTING);
    }

}
