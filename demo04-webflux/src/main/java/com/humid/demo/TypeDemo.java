package com.humid.demo;

/**
 * 类型引用
 * @author qiujianping
 * @date Created in 2021/8/12 9:25
 */
public class TypeDemo {
    public static void main(String[] args) {
        // 变量类型定义
        IMath math = (x, y) -> x + y;
        IMath math2 = Integer::sum;
        int add = math.add(1, 4);
        System.out.println(add);

        // 数组中
        IMath[] mathArray = {(x, y) -> x + y};
        IMath[] mathArray2 = {Integer::sum};

        // 强转
        Object obj = (IMath) Integer::sum;

        // 通过返回类型返回
        IMath math1 = getMath();

        TypeDemo typeDemo = new TypeDemo();
        // 当有两个重载方法时，需要使用强转来解决
        typeDemo.test((IMath) (x, y) -> x + y);

    }

    public void test(IMath iMath) {

    }

    public void test(IMath2 iMath2) {

    }

    public static IMath getMath() {
        return ((x, y) -> x + y);
    }
}

@FunctionalInterface
interface IMath {
    int add(int x, int y);
}

@FunctionalInterface
interface  IMath2 {
    int sub(int x, int y);
}
