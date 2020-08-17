package com.qjp.rabbitmq.study.consumer;

import com.qjp.rabbitmq.study.constant.RabbitMQConstant;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @Author: qiujianping
 * @Date: Created in 2020/8/17 11:56
 * @Description:
 */
@Component
@RabbitListener(queues = {RabbitMQConstant.RABBITMQ_DEMO_TOPIC})
public class RabbitDemoConsumer {
    @RabbitHandler
    public void process(Map map) {
        System.out.println("消费者收到的消息：" +  map);
    }
}
