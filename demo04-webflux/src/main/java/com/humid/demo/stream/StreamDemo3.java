package com.humid.demo.stream;

import java.util.Random;
import java.util.stream.Stream;

/**
 * @author qiujianping
 * @date Created in 2021/8/12 14:26
 */
public class StreamDemo3 {
    public static void main(String[] args) {
        String str = "my name is xiaoming";
        // 把每个单词的长度打印
        Stream.of(str.split(" ")).map(s -> s.length()).forEach(System.out::println);
        // 等价于
        Stream.of(str.split(" ")).map(String::length).forEach(System.out::println);

        // flatMap A->B 属性（是个集合），最终得到所有的A元素里面的所有B属性集合
        // IntStream / LongStream 并不是Stream的子类，所以需要进行装箱 boxed()
        Stream.of(str.split(" ")).flatMap(s -> s.chars().boxed()).forEach(i -> {
            System.out.println((char)i.intValue());
        });

        // peek 用于 debug 是个中间操作，和forEach是终止操作
        System.out.println("--------- peek ----------");
        Stream.of(str.split(" ")).peek(System.out::println).forEach(System.out::println);

        // limit 使用。主要用于无限流
        new Random().ints().limit(10).forEach(System.out::println);
    }
}
