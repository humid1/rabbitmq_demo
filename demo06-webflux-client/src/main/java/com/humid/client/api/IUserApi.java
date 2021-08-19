package com.humid.client.api;

import com.humid.client.annotation.ApiServer;
import com.humid.client.entity.User;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author qiujianping
 * @date Created in 2021/8/18 10:15
 */
@ApiServer("http://127.0.0.1:8090/user")
public interface IUserApi {

    @GetMapping("/")
    Flux<User> getAllUser();

    @GetMapping("/{id}")
    Mono<User> getUserById(@PathVariable("id") String id);

    @DeleteMapping("/{id}")
    Mono<Void> deleteUserById(@PathVariable("id") String id);

    @PostMapping("/")
    Mono<User> createUser(@RequestBody Mono<User> user);

}
