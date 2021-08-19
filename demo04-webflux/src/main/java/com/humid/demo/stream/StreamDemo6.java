package com.humid.demo.stream;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * map 和 flatMap的区别
 * @author qiujianping
 * @date Created in 2021/8/16 14:30
 */
public class StreamDemo6 {
    public static void main(String[] args) {
        System.out.println("=============== map ===============");
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("hello");
        arrayList.add("java");
        List<String> stringList = arrayList.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println(stringList);

        System.out.println("=============== flatMap =============");
        List<String> asList = Arrays.asList("hello c++", "hello java");
        List<String> result = asList.stream()
                .map(a -> a.split(" "))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(result);
    }
}
