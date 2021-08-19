package com.humid.client;

import com.humid.client.api.IUserApi;
import com.humid.client.proxy.CreatorProxy;
import com.humid.client.proxy.JdkCreatorProxy;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author qiujianping
 * @date Created in 2021/8/18 10:30
 */
@SpringBootApplication
public class WebfluxClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebfluxClientApplication.class, args);
    }

    @Bean
    CreatorProxy getCreator() {
        return new JdkCreatorProxy();
    }

    @Bean
    FactoryBean<IUserApi> userApiFactoryBean (CreatorProxy creatorProxy) {
        return new FactoryBean<IUserApi>() {

            /**
             * 返回代理对象
             */
            @Override
            public IUserApi getObject() throws Exception {
                return (IUserApi) creatorProxy.createProxy(IUserApi.class);
            }

            @Override
            public Class<?> getObjectType() {
                return null;
            }
        };
    }
}
