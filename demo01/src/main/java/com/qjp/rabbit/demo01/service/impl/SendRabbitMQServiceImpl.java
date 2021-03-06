package com.qjp.rabbit.demo01.service.impl;

import com.qjp.rabbit.demo01.constant.RabbitConstant;
import com.qjp.rabbit.demo01.service.SendRabbitMQService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: qiujianping
 * @Date: Created in 2020/8/17 11:30
 * @Description:
 */
@Service
public class SendRabbitMQServiceImpl implements SendRabbitMQService {

    /**
     * 日期格式化
     */
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final Logger logger = LoggerFactory.getLogger(SendRabbitMQServiceImpl.class);

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Override
    public String sendDirectMsg(String msg) {
        try {
            Map<String, Object> map = getMessage(msg);
            rabbitTemplate.convertAndSend(RabbitConstant.RABBITMQ_DEMO_DIRECT_EXCHANGE, RabbitConstant.RABBITMQ_DEMO_DIRECT_ROUTING, map);
            return "success";
        } catch (Exception e) {
            logger.error("出现异常：", e);
            return e.getMessage();
        }
    }

    @Override
    public String sendFanoutMsg(String msg) {
        try {
            Map<String, Object> map = getMessage(msg);
            rabbitTemplate.convertAndSend(RabbitConstant.FANOUT_EXCHANGE_DEMO_NAME, "", map);
            return "success";
        } catch (Exception e) {
            logger.error("出现异常：", e);
            return e.getMessage();
        }
    }

    @Override
    public String sendTopicMsg(String msg, String routerKey) {
        try {
            Map<String, Object> map = getMessage(msg);
            rabbitTemplate.convertAndSend(RabbitConstant.TOPIC_EXCHANGE_DEMO, routerKey, map);
            return "success";
        } catch (Exception e) {
            logger.error("出现异常：", e);
            return e.getMessage();
        }
    }

    @Override
    public String sendHeadersMsg(String msg, Map<String, Object> map) {
        try {
            MessageProperties messageProperties = new MessageProperties();
            // 消息持久化
            messageProperties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
            messageProperties.setContentType("UTF-8");
            // 添加消息
            messageProperties.getHeaders().putAll(map);
            Message message = new Message(msg.getBytes(), messageProperties);
            rabbitTemplate.convertAndSend(RabbitConstant.HEADERS_EXCHANGE_DEMO, null, message);
            return "success";
        } catch (Exception e) {
            logger.error("出现异常：", e);
            return e.getMessage();
        }
    }

    private Map<String, Object> getMessage(String msg) {
        String msgId = UUID.randomUUID().toString().replace("-", "").substring(0, 32);
        String sendTime = sdf.format(new Date());
        Map<String, Object> map = new HashMap<>();
        map.put("msgId", msgId);
        map.put("sendTime", sendTime);
        map.put("msg", msg);
        return map;
    }
}
