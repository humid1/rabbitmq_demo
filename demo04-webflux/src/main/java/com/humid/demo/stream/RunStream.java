package com.humid.demo.stream;

import java.util.Random;
import java.util.stream.Stream;

/**
 * 验证 stream 运行机制
 * @author qiujianping
 * @date Created in 2021/8/13 10:14
 */
public class RunStream {
    public static void main(String[] args) {
        /*
         * 1. 所有操作是链式调用，一个元素只迭代一次
         * 2. 每一个中间操作返回一个新的流，流中有个属性sourceStage 指向同一个地方就是 Head
         * 3. Head -> nextStage -> nextStage -> ... -> null
         * 4. 有状态操作会把无状态操作阶段，单独处理
         * 5. 并行环境下，有状态的中间操作不一定能并行操作
         */
        Random random = new Random();
        // 随机产生数据
        Stream<Integer> integerStream = Stream.generate(random::nextInt)
                .parallel()
                // 产生500条数据
                .limit(500)
                // 第1个无状态操作
                .peek(s -> print("peek -> " + s))
                // 第2个无状态操作
                .filter(s -> {
                    print("filter -> " + s);
                    return s > 1000;
                })
                // 有状态操作
                .sorted((i1, i2) -> {
                    print("排序 -> " + i1 + ", " + i2);
                    return i1.compareTo(i2);
                })
                // 无状态操作
                .peek(s -> print("peek2 -> " + s));

        long count = integerStream.count();

    }

    private static void print(String str) {
        System.out.println(Thread.currentThread().getName() + " " + str);
    }
}
