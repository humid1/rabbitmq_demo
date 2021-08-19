package com.humid.webflux.advice;

import com.humid.webflux.exceptions.CheckException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

/**
 * @author qiujianping
 * @date Created in 2021/8/16 10:27
 */
@ControllerAdvice
public class CheckAdvice {

    /**
     * servlet 容器
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleBindException(MethodArgumentNotValidException e) {
        return new ResponseEntity<String>(toStr(e), HttpStatus.BAD_REQUEST);
    }

    /**
     * netty 容器
     * @param e
     * @return
     */
    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<String> handleBindException2(WebExchangeBindException e) {
        return new ResponseEntity<String>(toStr(e), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CheckException.class)
    public ResponseEntity<String> checkException(CheckException e) {
        return new ResponseEntity<String>(toStr(e), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String toStr(CheckException e) {
        return e.getFieldName() + ": 错误的值 " + e.getFieldValue();
    }


    /**
     * 把校验异常转换为字符串
     * @param e
     * @return
     */
    private String toStr(MethodArgumentNotValidException e) {
        return e.getBindingResult().getFieldErrors().stream()
                .map(ex -> ex.getField() + ":" + ex.getDefaultMessage())
                .reduce("", (s1, s2) -> s1 + "\n" + s2);
    }


    /**
     * 把校验异常转换为字符串
     * @param e
     * @return
     */
    private String toStr(WebExchangeBindException e) {
        return e.getBindingResult().getFieldErrors().stream()
                .map(ex -> ex.getField() + ":" + ex.getDefaultMessage())
                .reduce("", (s1, s2) -> s1 + "\n" + s2);
    }
}
