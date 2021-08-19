package com.humid.demo.stream;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author qiujianping
 * @date Created in 2021/8/12 15:18
 */
public class StreamDemo5 {
    public static void main(String[] args) {
        // 调用 parallel 产生一个并行流
        // IntStream.range(0,100).parallel().peek(StreamDemo5::debug).count();

        /*
            需要实现：先并行在串行执行
            多次调用 parallel (并行) / sequential(串行),以最后一次调用为准
         */
        // IntStream.range(0,100)
        //         .parallel().peek(StreamDemo5::debug)
        //         .sequential().peek(StreamDemo5::debug2)
        //         .count();

        // 并行流使用线程池 ForkJoinPool.commonPool, 默认的线程是当前机器的 cpu 个数，使用这个属性可以修改默认的线程数
        // System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "20");
        // IntStream.range(0,100).parallel().peek(StreamDemo5::debug).count();

        // 使用自己创建的线程池,不使用默认线程池,防止任务被阻塞
        // 线程池名称：ForkJoinPool-1
        ForkJoinPool forkJoinPool = new ForkJoinPool(20);
        forkJoinPool.execute(() -> {
            IntStream.range(0,100).parallel().peek(StreamDemo5::debug).count();
        });
        forkJoinPool.shutdown();

        synchronized(forkJoinPool) {
            try {
                forkJoinPool.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void debug(Integer num) {
        System.out.println(Thread.currentThread().getName() + " debug -> " + num);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void debug2(Integer num) {
        System.err.println("debug2 -> " + num);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
