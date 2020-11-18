package com.qjp.rabbitmq.deadmq.entity;

/**
 * @author qiujianping
 * @date Created in 2020/11/18 17:35
 */
public enum DelayTypeEnum {
    /**
     * 10s 延时队列
     */
    DELAY_10s(),

    /**
     * 60s 延时队列
     */
    DELAY_60s();

    public static DelayTypeEnum getDelayTypeEnumByValue(Integer delayType) {
        switch (delayType) {
            case 1:
                return DELAY_10s;
            case 2:
                return DELAY_60s;
            default:
                return null;
        }
    }
}
