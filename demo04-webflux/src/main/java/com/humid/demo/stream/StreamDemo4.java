package com.humid.demo.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author qiujianping
 * @date Created in 2021/8/12 14:49
 */
public class StreamDemo4 {
    public static void main(String[] args) {
        String str = "my name is 007";

        // 使用 并行流 打印
        str.chars().parallel().forEach(i -> System.out.print((char) i));
        System.out.println("\r\n------------------------");

        // 使用 forEachOrdered 保证并行的执行顺序
        str.chars().parallel().forEachOrdered(i -> System.out.print((char) i));

        System.out.println();
        // 收集到 list
        List<String> list = Stream.of(str.split(" ")).collect(Collectors.toList());
        System.out.println(list);

        // 使用 reduce 拼接字符串
        Optional<String> reduce = Stream.of(str.split(" ")).reduce((s1, s2) -> s1 + "|" + s2);
        System.out.println(reduce.orElse(""));

        // 带初始值的 reduce
        String reduce2 = Stream.of(str.split(" ")).reduce("", (s1, s2) -> s1 + "|" + s2);
        System.out.println(reduce2);

        // 计算单词总长度
        Integer length = Stream.of(str.split(" ")).map(String::length).reduce(0, (s1, s2) -> s1 + s2);
        Integer length2 = Stream.of(str.split(" ")).map(String::length).reduce(0, Integer::sum);
        System.out.println(length);

        // 计算最长的单词 max
        Optional<String> max = Stream.of(str.split(" ")).max((s1, s2) -> (s1.length() - s2.length()));
        Optional<String> max2 = Stream.of(str.split(" ")).max(Comparator.comparingInt(String::length));
        System.out.println(max.get());

        // 使用 findFirst 短路操作 (只需要第一个数据)
        OptionalInt first = new Random().ints().findFirst();
        System.out.println(first.getAsInt());


    }
}
