package com.humid.client.proxy;

/**
 * 创建代理类接口
 * @author qiujianping
 * @date Created in 2021/8/18 10:34
 */
public interface CreatorProxy {
    /**
     * 创建代理类
     * @param type
     * @return
     */
    Object createProxy(Class<?> type);
}
