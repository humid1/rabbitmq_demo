package com.qjp.rabbit.demo01.config;

import com.qjp.rabbit.demo01.constant.RabbitConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;


/**
 * @Author: qiujianping
 * @Date: Created in 2020/8/17 10:26
 * @Description: Direct交换机以及队列的配置类
 *    实现BeanPostProcessor类，使用Bean的生命周期函数
 */
@Configuration
public class DirectRabbitConfig implements BeanPostProcessor {

    //这是创建交换机和队列用的rabbitAdmin对象
    @Resource
    private RabbitAdmin rabbitAdmin;

    @Bean
    public Queue rabbitmqDemoDirectQueue() {
        /**
         * 1. name: 队列名称
         * 2. durable: 是否持久化
         * 3. exclusive: 是否独享、排外。如果设置为 true，定义为排他队列，则只有创建者可以使用此队列
         * 4. autoDelete：是否自动删除，也就是临时队列。当最后一个消费者断开连接后，会自动删除
         */
        return new Queue(RabbitConstant.RABBITMQ_DEMO_TOPIC, true, false, false);
    }

    @Bean
    public DirectExchange rabbitmqDemoDirectExchange() {
        /**
         *  Direct 交换机
         *  1. name: 交换机名称
         *  2. durable: 是否持久化
         *  3. autoDelete: 是否自动删除
         */
        return new DirectExchange(RabbitConstant.RABBITMQ_DEMO_DIRECT_EXCHANGE, true ,false);
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
                .with(RabbitConstant.RABBITMQ_DEMO_DIRECT_ROUTING);
    }

    //初始化rabbitAdmin对象
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        // 只有设置为 true，spring 才会加载 RabbitAdmin 这个类
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }

    //实例化bean后，也就是Bean的后置处理器
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        //创建交换机
        rabbitAdmin.declareExchange(rabbitmqDemoDirectExchange());
        //创建队列
        rabbitAdmin.declareQueue(rabbitmqDemoDirectQueue());
        return null;
    }

}
