package com.qjp.rabbitmq.deadmq.sender;

import com.qjp.rabbitmq.deadmq.entity.DelayTypeEnum;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.qjp.rabbitmq.deadmq.config.RabbitMQConfig.*;

/**
 * @author qiujianping
 * @date Created in 2020/11/18 17:33
 */
@Component
public class DelayMessageSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMsg(String msg, DelayTypeEnum type){
        switch (type){
            case DELAY_10s:
                rabbitTemplate.convertAndSend(DELAY_EXCHANGE_NAME, DELAY_QUEUEA_ROUTING_KEY, msg);
                break;
            case DELAY_60s:
                rabbitTemplate.convertAndSend(DELAY_EXCHANGE_NAME, DELAY_QUEUEB_ROUTING_KEY, msg);
                break;
             default:
        }
    }
}
