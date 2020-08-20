package com.qjp.rabbitmq.study.consumer;

import com.qjp.rabbitmq.study.constant.RabbitConstant;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author qiujianping
 * @date Created in 2020/8/20 17:44
 */
@Component
@RabbitListener(queuesToDeclare = @Queue(RabbitConstant.HEADERS_EXCHANGE_QUEUE_A))
public class HeadersExchangeConsumerA {
    @RabbitListener(queuesToDeclare = @Queue(RabbitConstant.HEADERS_EXCHANGE_QUEUE_A))
    public void process(Message message) throws Exception {
        MessageProperties messageProperties = message.getMessageProperties();
        String contentType = messageProperties.getContentType();
        System.out.println("【headers】队列[" + RabbitConstant.HEADERS_EXCHANGE_QUEUE_A + "]收到消息：" + new String(message.getBody(), contentType));
    }
}
