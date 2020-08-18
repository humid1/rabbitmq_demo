package com.qjp.rabbitmq.study.config;

import com.qjp.rabbitmq.study.constant.RabbitConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @Author: qiujianping
 * @Date: Created in 2020/8/18 17:19
 * @Description: fanout_exchange 方式的交换机和队列配置
 */
@Configuration
public class FanoutRabbitConfig implements BeanPostProcessor {
    @Resource
    private RabbitAdmin rabbitAdmin;

    @Bean
    public Queue fanoutExchangeQueueA() {
        /**
         * 1. name: 队列名称
         * 2. durable: 是否持久化
         * 3. exclusive: 是否独享、排外。如果设置为 true，定义为排他队列，则只有创建者可以使用此队列
         * 4. autoDelete：是否自动删除，也就是临时队列。当最后一个消费者断开连接后，会自动删除
         */
        return new Queue(RabbitConstant.FANOUT_EXCHANGE_QUEUE_TOPIC_A, true, false, false);
    }
    @Bean
    public Queue fanoutExchangeQueueB() {
        /**
         * 1. name: 队列名称
         * 2. durable: 是否持久化
         * 3. exclusive: 是否独享、排外。如果设置为 true，定义为排他队列，则只有创建者可以使用此队列
         * 4. autoDelete：是否自动删除，也就是临时队列。当最后一个消费者断开连接后，会自动删除
         */
        return new Queue(RabbitConstant.FANOUT_EXCHANGE_QUEUE_TOPIC_B, true, false, false);
    }

    @Bean
    public FanoutExchange fanoutExchangeDemoName() {
        return new FanoutExchange(RabbitConstant.FANOUT_EXCHANGE_DEMO_NAME, true, false);
    }

    @Bean
    public Binding bindFanoutA() {
        return BindingBuilder.bind(fanoutExchangeQueueA()).to(fanoutExchangeDemoName());
    }

    @Bean
    public Binding bindFanoutB() {
        return BindingBuilder.bind(fanoutExchangeQueueB()).to(fanoutExchangeDemoName());
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // 启动项目即创建交换机和队列
        rabbitAdmin.declareExchange(fanoutExchangeDemoName());
        rabbitAdmin.declareQueue(fanoutExchangeQueueA());
        rabbitAdmin.declareQueue(fanoutExchangeQueueB());
        return null;
    }
}
