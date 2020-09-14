package com.qjp.rabbit.demo02.service;

/**
 * @author qiujianping
 * @date Created in 2020/9/14 16:22
 */
public interface MsgLogService {
    /**
     * 更新消息状态
     */
    void updateStatus(String msgId, String status);


}
