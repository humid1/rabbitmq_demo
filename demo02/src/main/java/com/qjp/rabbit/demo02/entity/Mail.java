package com.qjp.rabbit.demo02.entity;

import java.io.Serializable;

/**
 * @author qiujianping
 * @date Created in 2020/9/14 16:03
 */
public class Mail implements Serializable {
    private static final long serialVersionUID = -7143887278987150592L;
    /**
     * 目标邮箱
     */
    private String to;
    /**
     * 邮件标题
     */
    private String title;
    /**
     * 邮件内容
     */
    private String content;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "to='" + to + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
