package com.humid.client.handle;

import com.humid.client.bean.MethodInfo;
import com.humid.client.bean.ServerInfo;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @author qiujianping
 * @date Created in 2021/8/18 15:05
 */
public class WebClientRestHandle implements RestHandle{

    private WebClient webClient;
    /**
     * 初始化webclient
     * @param serverInfo
     */
    @Override
    public void init(ServerInfo serverInfo) {
        this.webClient = WebClient.create(serverInfo.getUrl());
    }

    /**
     * 处理rest请求
     * @param methodInfo
     * @return
     */
    @Override
    public Object invokeRest(MethodInfo methodInfo) {
        // 返回结果
        Object result = null;
        WebClient.RequestBodySpec bodySpec = this.webClient
                // 请求方法
                .method(methodInfo.getMethod())
                // 请求url
                .uri(methodInfo.getUrl(), methodInfo.getParams())
                // 请求类型
                .accept(MediaType.APPLICATION_JSON_UTF8);

        // 判断是否携带body
        if (null != methodInfo.getBody()) {
            bodySpec.body(methodInfo.getBody(), methodInfo.getBodyElementType());
        }
        WebClient.ResponseSpec response = bodySpec.retrieve();

        // 处理异常
        response.onStatus(httpStatus -> httpStatus.value() == 404,
                r -> Mono.just(new RuntimeException("not Found")));

        // 判断是否是 flux 类型
        if (methodInfo.isReturnFlux()) {
            // 处理body
            result = response.bodyToFlux(methodInfo.getReturnElementType());
        } else {
            result = response.bodyToMono(methodInfo.getReturnElementType());
        }

        return result;
    }
}
