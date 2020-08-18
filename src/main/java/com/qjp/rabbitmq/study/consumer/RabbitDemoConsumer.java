package com.qjp.rabbitmq.study.consumer;

import com.qjp.rabbitmq.study.constant.RabbitConstant;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: qiujianping
 * @Date: Created in 2020/8/17 11:56
 * @Description: 消费端 (单独写一个服务效果更明显)
 */
@Component
@RabbitListener(queues = {RabbitConstant.RABBITMQ_DEMO_TOPIC})
public class RabbitDemoConsumer {
    @RabbitHandler
    public void process(Map map) {
        System.out.println("消费者收到的消息：" +  map);
    }
}
