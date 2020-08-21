package com.qjp.rabbit.demo01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @Author: qiujianping
 * @Date: Created in 2020/8/18 18:02
 * @Description:
 */
@Configuration
public class Swagger2Configuration {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.qjp.rabbit.demo01.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("RabbitMQ Demo Api 文档")
                .description("RabbitMQ Demo API 网关接口，http://127.0.0.1:8080")
                .termsOfServiceUrl("https://github.com/humid1/rabbitmq_demo")
                .version("1.0.0")
                .build();
    }
}
