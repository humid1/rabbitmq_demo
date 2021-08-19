package com.humid.client.controller;

import com.humid.client.api.IUserApi;
import com.humid.client.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author qiujianping
 * @date Created in 2021/8/18 10:23
 *
 */
@RestController
public class TestController {

    @Autowired
    private IUserApi userApi;

    @GetMapping("/")
    public void test() {
        // 测试信息提取
        // userApi.getAllUser();
        // userApi.getUserById("1234");
        // userApi.deleteUserById("123456789");
        // User user = new User("qwe", 33);
        // userApi.createUser(Mono.just(user));

        // 直接调用，实现调用rest接口效果
        // Flux<User> userFluxs = userApi.getAllUser();
        // userFluxs.subscribe(System.out::println);

        userApi.getUserById("6116351d3a0efb14d08653a6").subscribe(v -> {
            System.out.println("getUserById: " + v);
        }, e -> {
            System.out.println("未找到用户信息！" + e.getMessage());
        });
        // userApi.deleteUserById("6116351d3a0efb14d08653a6").subscribe();

        // User user = new User("qwe", 33);
        // userApi.createUser(Mono.just(user)).subscribe(System.out::println);

    }
}
