package com.humid.demo;

import io.swagger.models.auth.In;

import java.util.function.Function;

/**
 * 级联表达式和柯里化
 *   柯里化: 把多个参数的函数转换为只有一个参数的函数
 *   柯里化目的: 函数标准化
 *   高阶函数：就是函数返回函数
 * @author qiujianping
 * @date Created in 2021/8/12 9:52
 */
public class CurryDemo {
    public static void main(String[] args) {
        // 实现了 x + y 级联表达式
        Function<Integer, Function<Integer, Integer>> function1 = x -> y -> x + y;
        System.out.println(function1.apply(1).apply(6));

        Function<Integer, Function<Integer, Function<Integer, Integer>>> function2 = (x -> y -> z -> x + y + z);
        System.out.println(function2.apply(1).apply(2).apply(3));

        // 循环调用
        Function f = function2;
        Integer[] arrays = {1, 5, 4};
        for (Integer array : arrays) {
            if (f instanceof Function) {
                Object apply = f.apply(array);
                if (apply instanceof Function) {
                    f = (Function) apply;
                } else {
                    System.out.println("执行结束，调用结果为：" + apply);
                }
            }
        }
    }
}
