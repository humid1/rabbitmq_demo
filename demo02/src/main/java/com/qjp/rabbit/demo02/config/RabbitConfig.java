package com.qjp.rabbit.demo02.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @author qiujianping
 * @date Created in 2020/9/14 16:18
 */
@Configuration
public class RabbitConfig {
    @Autowired
    private CachingConnectionFactory connectionFactory;


}
