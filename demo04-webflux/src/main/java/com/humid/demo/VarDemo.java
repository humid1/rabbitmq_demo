package com.humid.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * 变量引用
 * @author qiujianping
 * @date Created in 2021/8/12 9:43
 */
public class VarDemo {
    public static void main(String[] args) {
        // 使用lambda表达式，不能改变值的引用,
        final String str = "变量类型的值是：";
        final List<String> list = new ArrayList<>();
        Consumer<String> callback = s -> System.out.println(str + s);
        callback.accept("123456");
    }
}
