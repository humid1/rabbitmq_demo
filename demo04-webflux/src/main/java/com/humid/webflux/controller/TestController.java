package com.humid.webflux.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author qiujianping
 * @date Created in 2021/8/13 11:47
 */
@RestController
public class TestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/get1")
    public String get1() {
        logger.info("get1 start...");
        String result = createStr();
        logger.info("get1 end...");
        return result;
    }

    private String createStr() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "some string";
    }

    @GetMapping("/get2")
    public Mono<String> get2() {
        logger.info("get2 start...");
        Mono<String> mono = Mono.fromSupplier(this::createStr);
        logger.info("get2 end...");
        return mono;
    }

    /**
     * flux 使用，text/event-stream 输出为流
     * @return
     */
    @GetMapping(value = "/get3", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> get3() {
        Flux<String> stringFlux = Flux.fromStream(IntStream.range(1, 5).mapToObj(i -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "flux data ---> " + i;
        }));
        return stringFlux;
    }

}
