package com.humid.demo;

import java.text.DecimalFormat;
import java.util.function.Function;

/**
 * 函数调用案例
 * @author qiujianping
 * @date Created in 2021/8/11 17:36
 *
 */
public class MoneyDemo {
    public static void main(String[] args) {
        MyMoney myMoney = new MyMoney(99999999);
        myMoney.printMoney(i -> new DecimalFormat("#,###").format(i));
        // 链式写法
        Function<Integer, String> myMoneyInterface = (i -> new DecimalFormat("#,###").format(i));
        // 在返回值追加数据
        myMoney.printMoney(myMoneyInterface.andThen(s -> "现金：" + s));
    }
}

class MyMoney {

    private final int money;

    public MyMoney(int money) {
        this.money = money;
    }

    public void printMoney(Function<Integer, String> myMoneyInterface) {
        // System.out.println("money = " + myMoneyInterface.format(money));
        System.out.println("money = " + myMoneyInterface.apply(money));
    }
}

/**
 * 可以去除写在调用的方法体上
 */
// interface IMyMoneyInterface {
//     String format(int money);
// }