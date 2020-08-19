package com.qjp.rabbitmq.study.consumer;

import com.qjp.rabbitmq.study.constant.RabbitConstant;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author qiujianping
 * @date Created in 2020/8/19 15:25
 */
@Component
@RabbitListener(queuesToDeclare = @Queue(RabbitConstant.TOPIC_QUEUE_B))
public class TopicExchangeConsumerB {
    @RabbitHandler
    public void process(Map<String, Object> map) {
        System.out.println("【topic模式】" + RabbitConstant.TOPIC_QUEUE_B + "的消息为: " + map);
    }
}
