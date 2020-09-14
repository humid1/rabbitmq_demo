package com.qjp.rabbit.demo02.mapper;

import com.qjp.rabbit.demo02.entity.MsgLog;

/**
 * @author qiujianping
 * @date Created in 2020/9/14 17:31
 */
public interface MsgLogMapper {
    /**
     * 更新发送状态
     * @param msgId
     * @param status
     */
    void updateStatus(String msgId, String status);
    /**
     * 新增消息
     */
    void insert(MsgLog msgLog);
}
