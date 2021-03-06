package com.qjp.rabbit.demo01.consumer;

import com.qjp.rabbit.demo01.constant.RabbitConstant;
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
@RabbitListener(queuesToDeclare = @Queue(RabbitConstant.HEADERS_EXCHANGE_QUEUE_B))
public class HeadersExchangeConsumerB {
    @RabbitListener(queuesToDeclare = @Queue(RabbitConstant.HEADERS_EXCHANGE_QUEUE_B))
    public void process(Message message) throws Exception {
        MessageProperties messageProperties = message.getMessageProperties();
        String contentType = messageProperties.getContentType();
        System.out.println("【headers】队列[" + RabbitConstant.HEADERS_EXCHANGE_QUEUE_B + "]收到消息：" + new String(message.getBody(), contentType));
    }
}
