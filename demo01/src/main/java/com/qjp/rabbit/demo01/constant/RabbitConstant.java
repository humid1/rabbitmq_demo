package com.qjp.rabbit.demo01.constant;

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

    /**
     * topic_exchange 交换机类型：这种交换机是使用通配符去匹配，路由到对应的队列。通配符有两种："*" 、 "#"。需要注意的是通配符前面必须要加上"."符号。
     *  * 符号：有且只匹配一个词。比如 a.*可以匹配到"a.b"、"a.c"，但是匹配不了"a.b.c"。
     *  # 符号：匹配一个或多个词。比如"rabbit.#"既可以匹配到"rabbit.a.b"、"rabbit.a"，也可以匹配到"rabbit.a.b.c"
     */
    public static final String TOPIC_EXCHANGE_DEMO = "topic.exchange.demo";

    /**
     * 队列名称
     */
    public static final String TOPIC_QUEUE_A = "topic.queue.a";

    public static final String TOPIC_QUEUE_B = "topic.queue.b";

    public static final String TOPIC_QUEUE_C = "topic.queue.c";

    /**
     * Headers Exchange ：创建队列需要设置绑定的头部信息，有两种模式：全部匹配和部分匹配。
     * 交换机会根据生产者发送过来的头部信息携带的键值去匹配队列绑定的键值，路由到对应的队列。
     */
    public static final String HEADERS_EXCHANGE_DEMO = "headers.exchange.demo";

    /**
     * 队列名称
     */
    public static final String HEADERS_EXCHANGE_QUEUE_A = "headers.demo.a";

    public static final String HEADERS_EXCHANGE_QUEUE_B = "headers.demo.b";
}
