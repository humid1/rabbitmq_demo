package com.qjp.rabbit.demo01.service;

import java.util.Map;

/**
 * @Author: qiujianping
 * @Date: Created in 2020/8/17 11:29
 * @Description: 发送消息的Service类
 */
public interface SendRabbitMQService {

    String sendDirectMsg(String msg);

    String sendFanoutMsg(String msg);

    String sendTopicMsg(String msg, String routerKey);

    String sendHeadersMsg(String msg, Map<String, Object> map);
}