package com.humid.client.proxy;

import com.humid.client.annotation.ApiServer;
import com.humid.client.bean.MethodInfo;
import com.humid.client.bean.ServerInfo;
import com.humid.client.handle.RestHandle;
import com.humid.client.handle.WebClientRestHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author qiujianping
 * @date Created in 2021/8/18 10:39
 */
public class JdkCreatorProxy implements CreatorProxy {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Object createProxy(Class<?> type) {
        // 根据接口得到 api 服务器信息
        ServerInfo serverInfo = extractServerInfo(type);
        logger.info("serverInfo ===> {}", serverInfo);
        // 给每一个代理类实现
        RestHandle handle = new WebClientRestHandle();
        // 初始化服务器信息（初始化webclient）
        handle.init(serverInfo);
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{type}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 根据方法和参数得到调用信息
                MethodInfo methodInfo = extractMethodInfo(method, args);
                logger.info("methodInfo ===> {}", methodInfo);
                // 调用 rest
                return handle.invokeRest(methodInfo);
            }
        });
    }

    private MethodInfo extractMethodInfo(Method method, Object[] args) {
        MethodInfo methodInfo = new MethodInfo();
        extractedUrlMethod(method, methodInfo);
        // 得到调用的参数和body
        extractedRequestParamAndBody(method, args, methodInfo);
        // 提取返回对象信息
        extractedReturnInfo(method, methodInfo);
        return methodInfo;
    }

    private void extractedReturnInfo(Method method, MethodInfo methodInfo) {
        // 返回对象是 flux 还是 mono; isAssignableFrom 判断类型是否为某个子类; instanceof 判断实例是否为某个子类
        boolean isFlux = method.getReturnType().isAssignableFrom(Flux.class);
        methodInfo.setReturnFlux(isFlux);
        // 得到返回对象的实际类型
        Class<?> elementType = extractElementType(method.getGenericReturnType());
        methodInfo.setReturnElementType(elementType);
    }

    /**
     * 提取类信息
     * @param genericReturnType
     * @return
     */
    private Class<?> extractElementType(Type genericReturnType) {
        Type[] typeArguments = ((ParameterizedType) genericReturnType).getActualTypeArguments();
        return (Class<?>) typeArguments[0];
    }

    /**
     * 得到请求的 param 和 body
     * @param method
     * @param args
     * @param methodInfo
     */
    private void extractedRequestParamAndBody(Method method, Object[] args, MethodInfo methodInfo) {
        Parameter[] parameters = method.getParameters();

        Map<String, Object> params = new LinkedHashMap<>();

        for (int i = 0; i < parameters.length; i++) {
            // 是否带 @PathVariable
            PathVariable pathVariable = parameters[i].getAnnotation(PathVariable.class);
            if (null != pathVariable) {
                params.put(pathVariable.value(), args[i]);
            }
            // 返回 requestBody
            RequestBody requestBody = parameters[i].getAnnotation(RequestBody.class);
            if (null != requestBody) {
                methodInfo.setBody((Mono<?>) args[i]);
                // 请求对象的实际类型
                methodInfo.setBodyElementType(
                        (extractElementType(parameters[i].getParameterizedType()))
                );
            }
        }
        methodInfo.setParams(params);
    }

    /**
     * 得到请求的url和方法
     * @param method
     * @param methodInfo
     */
    private void extractedUrlMethod(Method method, MethodInfo methodInfo) {
        // 得到请求url和请求方法
        Annotation[] annotations = method.getAnnotations();
        for (Annotation annotation : annotations) {
            // get
            if (annotation instanceof GetMapping) {
                GetMapping getMapping = (GetMapping) annotation;
                methodInfo.setUrl(getMapping.value()[0]);
                methodInfo.setMethod(HttpMethod.GET);
            } else if (annotation instanceof PostMapping) {
                PostMapping getMapping = (PostMapping) annotation;
                methodInfo.setUrl(getMapping.value()[0]);
                methodInfo.setMethod(HttpMethod.POST);
            } else if (annotation instanceof DeleteMapping) {
                DeleteMapping getMapping = (DeleteMapping) annotation;
                methodInfo.setUrl(getMapping.value()[0]);
                methodInfo.setMethod(HttpMethod.DELETE);
            }
        }
    }

    private ServerInfo extractServerInfo(Class<?> type) {
        ServerInfo serverInfo = new ServerInfo();
        // 获取注解信息
        ApiServer anno = type.getAnnotation(ApiServer.class);
        serverInfo.setUrl(anno.value());
        return serverInfo;
    }
}
