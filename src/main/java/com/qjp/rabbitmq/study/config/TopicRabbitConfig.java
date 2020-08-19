package com.qjp.rabbitmq.study.config;

import com.qjp.rabbitmq.study.constant.RabbitConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author qiujianping
 * @date Created in 2020/8/19 14:58
 * topic_exchange 通配符交换机的配置
 */
@Configuration
public class TopicRabbitConfig implements BeanPostProcessor {
    @Resource
    private RabbitAdmin rabbitAdmin;

    @Bean
    public TopicExchange rabbitDemoTopicExchange() {
        // 1.交换机名称 2.是否持久化  3.是否自动删除，也就是临时队列。当最后一个消费者断开连接后，会自动删除
        return new TopicExchange(RabbitConstant.TOPIC_EXCHANGE_DEMO, true,false);
    }

    @Bean
    public Queue topicExchangeQueueA() {
        /**
         * 1. name: 队列名称
         * 2. durable: 是否持久化
         * 3. exclusive: 是否独享、排外。如果设置为 true，定义为排他队列，则只有创建者可以使用此队列
         * 4. autoDelete：是否自动删除，也就是临时队列。当最后一个消费者断开连接后，会自动删除
         */
        return new Queue(RabbitConstant.TOPIC_QUEUE_A, true, false, false);
    }

    @Bean
    public Queue topicExchangeQueueB() {
        return new Queue(RabbitConstant.TOPIC_QUEUE_B, true, false, false);
    }

    @Bean
    public Queue topicExchangeQueueC() {
        return new Queue(RabbitConstant.TOPIC_QUEUE_C, true, false, false);
    }

    /**
     * 队列绑定到交换机，增加通配符匹配的，路由键
     */
    @Bean
    public Binding bindTopicA() {
        return BindingBuilder.bind(topicExchangeQueueA())
                .to(rabbitDemoTopicExchange())
                .with("a.*");
    }

    @Bean
    public Binding bindTopicB() {
        return BindingBuilder.bind(topicExchangeQueueB())
                .to(rabbitDemoTopicExchange())
                .with("a.*");
    }

    @Bean
    public Binding bindTopicC() {
        return BindingBuilder.bind(topicExchangeQueueC())
                .to(rabbitDemoTopicExchange())
                .with("rabbit.#");
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // 启动项目就生成交换机和队列
        rabbitAdmin.declareExchange(rabbitDemoTopicExchange());
        rabbitAdmin.declareQueue(topicExchangeQueueA());
        rabbitAdmin.declareQueue(topicExchangeQueueB());
        rabbitAdmin.declareQueue(topicExchangeQueueC());
        return null;
    }
}
