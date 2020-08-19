package com.qjp.rabbitmq.study.controller;

import com.qjp.rabbitmq.study.service.SendRabbitMQService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(tags = "RabbitMQ Demo Api 生产者接口")
@RestController
@RequestMapping("/rabbitmq")
public class SendRabbitController {
    @Resource
    private SendRabbitMQService sendRabbitMQService;

    @ApiOperation(value = "topic模式,通配符匹配路由key")
    @PostMapping("/sendTopicMsg")
    public Object sendTopicMsg(@ApiParam(name = "msg", value = "发送的消息") @RequestParam(name = "msg") String msg,
                               @ApiParam(name = "routerKey", value = "路由key(a.xxx或rabbit.xxx)") @RequestParam(name = "routerKey") String routerKey) {
        String msg1 = sendRabbitMQService.sendTopicMsg(msg, routerKey);
        return getMsg(msg1);
    }

    @ApiOperation(value = "direct模式,匹配路由key")
    @PostMapping("/sendDirectMsg")
    public Object sendDirectMsg(@ApiParam(name = "msg", value = "发送的消息") @RequestParam(name = "msg") String msg) {
        String msg1 = sendRabbitMQService.sendDirectMsg(msg);
        return getMsg(msg1);
    }

    @ApiOperation(value = "fanout模式，广播模式")
    @PostMapping("/sendFanoutMsg")
    public Object sendFanoutMsg(@ApiParam(name = "msg", value = "发送的消息") @RequestParam(name = "msg") String msg) {
        String msg1 = sendRabbitMQService.sendFanoutMsg(msg);
        return getMsg(msg1);
    }

    private Object getMsg(String msg) {
        Map<String, String> map = new HashMap<>();
        if("success".equals(msg)) {
            map.put("code","0");
        } else {
            map.put("code", "1");
        }
        map.put("msg", msg);
        return map;
    }
}
