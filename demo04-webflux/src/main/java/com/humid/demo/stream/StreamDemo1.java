package com.humid.demo.stream;

import java.util.stream.IntStream;

/**
 * 流的使用
 * @author qiujianping
 * @date Created in 2021/8/12 11:10
 */
public class StreamDemo1 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        // 外部迭代
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        System.out.println("结果为：" + sum);

        // 使用 stream 的内部迭代
        // map 就是中间操作(返回 Stream 操作)，sum 就是终止操作
        // int sum1 = IntStream.of(nums).map(i -> i * 2).sum();
        int sum1 = IntStream.of(nums).map(StreamDemo1::doubleNum).sum();
        System.out.println("使用stream调用内部函数结果为：" + sum1);

        System.out.println("惰性求值就是终止没有调用的情况下，中间操作不会执行");
        IntStream.of(nums).map(StreamDemo1::doubleNum);
    }

    public static int doubleNum(int i) {
        System.out.println("执行了 x 2 操作");
        return i * 2;
    }

}
