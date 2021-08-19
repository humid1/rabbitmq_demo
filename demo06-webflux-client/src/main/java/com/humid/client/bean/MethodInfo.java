package com.humid.client.bean;

import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * @author qiujianping
 * @date Created in 2021/8/18 10:46
 */
public class MethodInfo {
    /**
     * 请求的url
     */
    private String url;

    /**
     * 请求方法
     */
    private HttpMethod method;

    /**
     * 请求参数（url）
     */
    private Map<String, Object> params;

    /**
     * 请求body
     */
    private Mono body;

    /**
     * 请求body的类型
     */
    private Class<?> bodyElementType;

    /**
     * 返回是flux还是Mono
     */
    private boolean returnFlux;

    /**
     * 返回对象类型
     */
    private Class<?> returnElementType;

    public Class<?> getBodyElementType() {
        return bodyElementType;
    }

    public void setBodyElementType(Class<?> bodyElementType) {
        this.bodyElementType = bodyElementType;
    }

    public boolean isReturnFlux() {
        return returnFlux;
    }

    public void setReturnFlux(boolean returnFlux) {
        this.returnFlux = returnFlux;
    }

    public Class<?> getReturnElementType() {
        return returnElementType;
    }

    public void setReturnElementType(Class<?> returnElementType) {
        this.returnElementType = returnElementType;
    }

    public MethodInfo() {

    }

    public MethodInfo(String url, HttpMethod method, Map<String, Object> params, Mono<?> body) {
        this.url = url;
        this.method = method;
        this.params = params;
        this.body = body;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public Mono getBody() {
        return body;
    }

    public void setBody(Mono body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "MethodInfo{" +
                "url='" + url + '\'' +
                ", method=" + method +
                ", params=" + params +
                ", body=" + body +
                ", bodyElementType=" + bodyElementType +
                ", returnFlux=" + returnFlux +
                ", returnElementType=" + returnElementType +
                '}';
    }
}
