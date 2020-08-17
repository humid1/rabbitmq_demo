package com.qjp.rabbitmq.study.service;

/**
 * @Author: qiujianping
 * @Date: Created in 2020/8/17 11:29
 * @Description: 发送消息的Service类
 */
public interface SendRabbitMQService {

    String sendMsg(String msg);

}
