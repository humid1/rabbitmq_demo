package com.humid.demo.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 * @author qiujianping
 * @date Created in 2021/8/12 11:45
 */
public class StreamDemo2 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        // 从集合中创建
        list.stream();
        list.parallelStream();

        // 从数组中创建
        int[] array = {1, 2, 3, 4};
        Arrays.stream(array);

        // 创建数字流
        IntStream.of(1, 2, 3);

        // 使用 random 创建一个无限流
        new Random().ints().limit(10);

        Random random = new Random();
        // 自己参生流
        Stream.generate(() -> random.nextInt()).limit(20);
    }
}
