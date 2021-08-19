package com.humid.client.bean;

/**
 * 服务器的url
 * @author qiujianping
 * @date Created in 2021/8/18 10:41
 */
public class ServerInfo {

    private String url;

    public ServerInfo() {
    }

    public ServerInfo(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ServerInfo{" +
                "url='" + url + '\'' +
                '}';
    }
}
