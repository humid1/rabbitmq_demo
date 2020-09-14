package com.qjp.rabbit.demo02.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author qiujianping
 * @date Created in 2020/9/14 18:24
 */
public class MsgLog implements Serializable {

    private static final long serialVersionUID = -973120108536021866L;

    private String msgId;

    private String msg;

    private String exchange;

    private String routingKey;

    private String status;

    private Long tryCount;

    private Date nextTryTime;

    private Date createTime;

    private Date updateTime;

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getTryCount() {
        return tryCount;
    }

    public void setTryCount(Long tryCount) {
        this.tryCount = tryCount;
    }

    public Date getNextTryTime() {
        return nextTryTime;
    }

    public void setNextTryTime(Date nextTryTime) {
        this.nextTryTime = nextTryTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "MsgLog{" +
                "msgId='" + msgId + '\'' +
                ", msg='" + msg + '\'' +
                ", exchange='" + exchange + '\'' +
                ", routingKey='" + routingKey + '\'' +
                ", status='" + status + '\'' +
                ", tryCount=" + tryCount +
                ", nextTryTime=" + nextTryTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
