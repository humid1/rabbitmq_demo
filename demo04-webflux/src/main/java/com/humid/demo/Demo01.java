package com.humid.demo;

import java.util.stream.IntStream;

/**
 * @author qiujianping
 * @date Created in 2021/8/11 17:35
 */
public class Demo01 {
    public static void main(String[] args) {
        int[] arrays = {1, 2, 99, -8, 0};
        int min = 0;
        for (int array : arrays) {
            if (min > array) {
                min = array;
            }
        }
        System.out.println(min);

        int asMin = IntStream.of(arrays).min().getAsInt();
        int asMax = IntStream.of(arrays).parallel().max().getAsInt();
        System.out.println(asMin);
        System.out.println(asMax);
    }
}
