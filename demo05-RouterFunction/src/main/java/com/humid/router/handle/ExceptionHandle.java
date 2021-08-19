package com.humid.router.handle;

import com.humid.router.exceptions.CheckException;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

/**
 * @author qiujianping
 * @date Created in 2021/8/17 17:39
 */
@Component
@Order(-2)
public class ExceptionHandle  implements WebExceptionHandler {
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();
        // 设置响应头 400
        response.setStatusCode(HttpStatus.BAD_REQUEST);
        // 设置返回类型
        response.getHeaders().setContentType(MediaType.TEXT_PLAIN);
        // 异常信息
        String errorMsg = toStr(ex);

        DataBuffer dataBuffer = response.bufferFactory().wrap(errorMsg.getBytes());
        return response.writeWith(Mono.just(dataBuffer));
    }

    private String toStr(Throwable ex) {
        if (ex instanceof CheckException) {
            // 已知异常
            CheckException checkException = (CheckException) ex;
            return checkException.getFieldName() + ": invaild value";
        } else {
            // 未知异常
            ex.printStackTrace();
            return ex.toString();
        }
    }

}
