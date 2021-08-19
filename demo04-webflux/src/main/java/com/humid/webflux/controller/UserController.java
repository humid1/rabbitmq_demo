package com.humid.webflux.controller;

import com.humid.webflux.entity.User;
import com.humid.webflux.repository.UserRepository;
import com.humid.webflux.util.CheckUtil;
import javafx.scene.input.DataFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.SimpleFormatter;

/**
 * @author qiujianping
 * @date Created in 2021/8/13 16:20
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserRepository userRepository;

    /**
     * 以对象形式一次性返回数据
     * @return
     */
    @GetMapping(value = "/")
    public Flux<User> getAll() {
        return userRepository.findAll();
    }

    /**
     * 以 SSE（Server-sent Events 服务发送事件） 方式多次返回数据
     * @return
     */
    @GetMapping(value = "/stream/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> streamGetAll() {
        return userRepository.findAll();
    }

    /**
     * 新增数据
     * @param user
     * @return
     */
    @PostMapping("/")
    public Mono<User> createUser(@Valid @RequestBody User user) {
        // spring data jpa, 新增修改都是save，有 id 是修改，id 为 null 为新增
        CheckUtil.checkName(user.getName());
        user.setId(null);
        return this.userRepository.save(user);
    }

    /**
     * 删除数据
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<String>> deleteUser(@PathVariable("id") String id) {
        // deleteById 无返回值，不能判断是否存在
        // this.userRepository.deleteById(id);
        return this.userRepository.findById(id)
                // 需要操作数据并返回Mono，需要使用 flatMap，不操作数据，只是转换数据就使用map
                .flatMap(user -> this.userRepository.delete(user)
                        .then(Mono.just(new ResponseEntity<String>("删除成功",HttpStatus.OK))))
                .defaultIfEmpty(new ResponseEntity<>("删除的id不存在", HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Object>> updateUser(@PathVariable("id") String id,
                                                   @Valid @RequestBody User user) {
        return this.userRepository.findById(id)
                .flatMap(u -> {
                    u.setAge(user.getAge());
                    u.setName(user.getName());
                    return this.userRepository.save(u);
                })
                .map(u -> new ResponseEntity<Object>(u, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>("要修改的id未找到", HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}")
    public Mono<Map<String, Object>> findById(@PathVariable("id") String id) {
        return this.userRepository.findById(id)
                .map(u -> this.getMap(200, u))
                .defaultIfEmpty(this.getMap(400, "查询失败"));
    }

    @GetMapping(value = "/age/{start}/{end}")
    public Flux<User> findByAge(@PathVariable("start") int start,
                                      @PathVariable("end") int end) {
        return this.userRepository.findByAgeBetween(start, end);
    }

    @GetMapping(value = "/stream/age/{start}/{end}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> streamFindByAge(@PathVariable("start") int start,
                                @PathVariable("end") int end) {
        return this.userRepository.findByAgeBetween(start, end);
    }

    @GetMapping("/old")
    public Flux<User> oldAge() {
        return this.userRepository.oldUser();
    }

    private <T> Map<String, Object> getMap(Integer code, T t) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        map.put("data", t);
        return map;
    }

}
