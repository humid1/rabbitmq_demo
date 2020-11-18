package com.qjp.rabbitmq.deadmq.controller;

import com.qjp.rabbitmq.deadmq.entity.DelayTypeEnum;
import com.qjp.rabbitmq.deadmq.sender.DelayMessageSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Objects;

/**
 * @author qiujianping
 * @date Created in 2020/11/18 17:40
 */
@RestController
@Slf4j
@RequestMapping("rabbitmq")
public class RabbitMQMsgController {

    @Autowired
    private DelayMessageSender sender;

    @RequestMapping("sendmsg")
    public void sendMsg(String msg, Integer delayType){
        log.info("当前时间：{},收到请求，msg:{},delayType:{}", new Date(), msg, delayType);
        sender.sendMsg(msg, Objects.requireNonNull(DelayTypeEnum.getDelayTypeEnumByValue(delayType)));
    }
}
