package com.qjp.rabbitmq.study.consumer;

import com.qjp.rabbitmq.study.constant.RabbitConstant;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: qiujianping
 * @Date: Created in 2020/8/18 17:44
 * @Description: fanout模式-消费者B
 */
@Component
@RabbitListener(queuesToDeclare = @Queue(RabbitConstant.FANOUT_EXCHANGE_QUEUE_TOPIC_A))
public class FanoutExchangeConsumerA {
    @RabbitHandler
    public void process(Map<String, Object> map) {
        System.out.println("【fanout】队列A 接收到的消息：" + map);
    }
}
