package com.qjp.rabbitmq.deadmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author qiujianping
 * @date Created in 2020/11/18 17:55
 */
@SpringBootApplication
public class DelayApplication {
    public static void main(String[] args) {
        SpringApplication.run(DelayApplication.class, args);
    }
}
