package com.qjp.rabbitmq.study.service.impl;

import com.qjp.rabbitmq.study.constant.RabbitConstant;
import com.qjp.rabbitmq.study.service.SendRabbitMQService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
            String msgId = UUID.randomUUID().toString().replace("-", "").substring(0, 32);
            String sendTime = sdf.format(new Date());
            Map<String, Object> map = new HashMap<>();
            map.put("msgId", msgId);
            map.put("sendTime", sendTime);
            map.put("msg", msg);
            rabbitTemplate.convertAndSend(RabbitConstant.RABBITMQ_DEMO_DIRECT_EXCHANGE, RabbitConstant.RABBITMQ_DEMO_DIRECT_ROUTING, map);
            return "ok";
        } catch (Exception e) {
            logger.error("出现异常：", e);
            return "error";
        }
    }

    @Override
    public String sendFanoutMsg(String msg) {
        return null;
    }
}