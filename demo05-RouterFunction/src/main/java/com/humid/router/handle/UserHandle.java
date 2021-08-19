package com.humid.router.handle;

import com.humid.router.entity.User;
import com.humid.router.repository.UserRepository;
import com.humid.router.util.CheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @author qiujianping
 * @date Created in 2021/8/17 10:38
 */
@Component
public class UserHandle {

    @Autowired
    private UserRepository userRepository;

    /**
     * 查询用户信息
     * @param request
     * @return
     */
    public Mono<ServerResponse> getAllUser(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(this.userRepository.findAll(), User.class);
    }

    /**
     * 创建用户
     */
    public Mono<ServerResponse> createUser(ServerRequest request) {
        Mono<User> userMono = request.bodyToMono(User.class);
        // return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
        //         .body(this.userRepository.saveAll(userMono), User.class);
        return userMono.flatMap(u -> {
            // 校验代码
            CheckUtil.checkName(u.getName());
            return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                    .body(this.userRepository.save(u), User.class);
        });
    }

    /**
     * 根据id删除用户
     */
    public Mono<ServerResponse> deleteUserById(ServerRequest request) {
        String id = request.pathVariable("id");
        return this.userRepository.findById(id)
                .flatMap(u -> this.userRepository.delete(u).then(ServerResponse.ok().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    /**
     * 获取单个用户信息
     */
    public Mono<ServerResponse> getUserById(ServerRequest request) {
        String id = request.pathVariable("id");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(this.userRepository.findById(id), User.class);
    }
}
