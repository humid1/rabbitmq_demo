package com.qjp.rabbit.demo01.consumer;

import com.qjp.rabbit.demo01.constant.RabbitConstant;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author qiujianping
 * @date Created in 2020/8/19 15:22
 */
@Component
@RabbitListener(queuesToDeclare = @Queue(RabbitConstant.TOPIC_QUEUE_A))
public class TopicExchangeConsumerA {
    @RabbitHandler
    public void process(Map<String, Object> map) {
        System.out.println("【topic模式】" + RabbitConstant.TOPIC_QUEUE_A + "的消息为: " + map);
    }
}
