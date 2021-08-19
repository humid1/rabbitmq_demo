package com.humid.router.routers;

import com.humid.router.handle.UserHandle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;

/**
 * @author qiujianping
 * @date Created in 2021/8/17 9:12
 */
@Configuration
public class AllRouters {

    @Bean
    RouterFunction<ServerResponse> userRouter(UserHandle userHandle) {
        return RouterFunctions.nest(
                // 相当于 @RequestMapping("/user")
                RequestPredicates.path("/user"),
                // 相当于 @GetMapping("/") 得到所有用户
                RouterFunctions
                        // 得到所有用户
                        .route(RequestPredicates.GET("/"), userHandle::getAllUser)
                        // 获取单个用户信息
                        .andRoute(RequestPredicates.GET("/{id}"), userHandle::getUserById)
                        // 创建用户
                        .andRoute(RequestPredicates.POST("/").and(RequestPredicates.accept(MediaType.APPLICATION_JSON_UTF8)), userHandle::createUser)
                        // 删除用户
                        .andRoute(RequestPredicates.DELETE("/{id}"), userHandle::deleteUserById)
        );
    }
}
