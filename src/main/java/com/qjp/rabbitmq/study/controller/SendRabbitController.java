package com.qjp.rabbitmq.study.controller;

import com.qjp.rabbitmq.study.service.SendRabbitMQService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: qiujianping
 * @Date: Created in 2020/8/17 11:40
 * @Description:
 */
@RestController
@RequestMapping("/rabbitmq")
public class SendRabbitController {
    @Resource
    private SendRabbitMQService sendRabbitMQService;

    /**
     * 发送消息
     */
    @PostMapping("/sendMsg")
    public String sendMsg(@RequestParam(name = "msg") String msg) {
        return sendRabbitMQService.sendMsg(msg);
    }
}
