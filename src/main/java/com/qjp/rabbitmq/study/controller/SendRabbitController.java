package com.qjp.rabbitmq.study.controller;

import com.qjp.rabbitmq.study.service.SendRabbitMQService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

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
    public Object sendDirectMsg(@RequestParam(name = "msg") String msg) {
        Map<String, String> map = new HashMap<>();
        String msg1 = sendRabbitMQService.sendDirectMsg(msg);
        if("ok".equals(msg1)) {
            map.put("code","0");
        } else {
            map.put("code", "1");
        }
        map.put("msg", msg1);
        return map;
    }
}
