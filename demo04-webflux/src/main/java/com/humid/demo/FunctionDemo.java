package com.humid.demo;

import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;

/**
 * 函数引用
 * @author qiujianping
 * @date Created in 2021/8/11 18:02
 */
public class FunctionDemo {
    public static void main(String[] args) {
        // 断言函数接口
        IntPredicate intPredicate = i -> i < 0;
        System.out.println(intPredicate.test(1));

        // 消费函数接口  System.out::println  =>  v -> System.out.println(v)
        IntConsumer intConsumer = (System.out::println);
        intConsumer.accept(100);

        Consumer<String> consumer = (System.out::println);
        consumer.accept("测试消费函数接口！！！");
    }
}
