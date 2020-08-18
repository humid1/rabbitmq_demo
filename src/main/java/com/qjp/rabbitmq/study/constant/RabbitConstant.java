package com.qjp.rabbitmq.study.constant;

/**
 * @Author: qiujianping
 * @Date: Created in 2020/8/17 10:36
 * @Description: rabbitmq的常量信息
 */
public class RabbitConstant {
    /**
     * direct_exchange 交换机类型：求该消息与一个特定的路由键完全匹配
     *  队列主题名称
     */
    public static final String RABBITMQ_DEMO_TOPIC = "demo.topic";
    /**
     * 队列交换机名称
     */
    public static final String RABBITMQ_DEMO_DIRECT_EXCHANGE = "demo.direct.exchange";
    /**
     * direct 交换机和队列绑定的匹配键 directRouting
     */
    public static final String RABBITMQ_DEMO_DIRECT_ROUTING = "demo.direct.routing";

    /**
     * fanout_exchange 交换机类型：一个发送到交换机的消息都会被转发到与该交换机绑定的所有队列上，类似广播
     *   队列 A 的名称
     */
    public static final String FANOUT_EXCHANGE_QUEUE_TOPIC_A = "fanout.A";

    /**
     * 队列 B 的名称
     */
    public static final String FANOUT_EXCHANGE_QUEUE_TOPIC_B = "fanout.B";

    /**
     * 交换机名称
     */
    public static final String FANOUT_EXCHANGE_DEMO_NAME = "fanout.exchange.demo";
}
