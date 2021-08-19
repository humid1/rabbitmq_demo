package com.humid.demo;

import com.google.common.base.Function;
import com.google.common.base.Supplier;
import com.rabbitmq.client.BlockedCallback;
import io.swagger.models.auth.In;

import java.sql.SQLOutput;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.IntUnaryOperator;
import java.util.function.UnaryOperator;

/**
 * 方法参数引用
 * @author qiujianping
 * @date Created in 2021/8/11 18:10
 *
 */
public class MethodReferenceDemo {
    public static void main(String[] args) {
        // 方法引用
        Consumer<String> callback = v -> System.out.println(v);
        // 等价于
        Consumer<String> callback2 = System.out::println;

        callback.accept("测试");
        callback2.accept("测试2");

        // 调用静态方法
        Consumer<Dog> dogConsumer = (Dog::bark);
        Dog dog = new Dog();
        dogConsumer.accept(dog);

        // 调用成员方法
        // Function<Integer, Integer> eatFunction = dog::eat;
        // UnaryOperator<Integer> eatFunction = dog::eat;
        // System.out.println("还剩下" + eatFunction.apply(1) + "斤");
        IntUnaryOperator eatFunction = dog::eat;
        System.out.println("还剩下" + eatFunction.applyAsInt(1) + "斤");

        // 使用类名来方法引用
        BiFunction<Dog, Integer, Integer> eatFunction2 = Dog::eat;
        Integer apply = eatFunction2.apply(dog, 22);
        System.out.println("还剩下" + apply + "斤");

        // 构造函数方法引用
        Supplier<Dog> supplier = Dog::new;
        System.out.println("1.创建了对象：" + supplier.get());

        // 带参数的构造函数的方法引用
        Function<String, Dog> stringDogFunction = Dog::new;
        System.out.println("2.创建了对象: " + stringDogFunction.apply("哈士奇"));
        BiFunction<String, Integer, Dog> dogSupplier = Dog::new;
        System.out.println("3.创建了对象: " + dogSupplier.apply("哈士奇", 10));
    }
}

class Dog {

    private String name = "小狗狗";

    private Integer food = 100;

    public Dog() {

    }

    public Dog(String name) {
        this.name = name;
    }

    public Dog(String name, Integer food) {
        this.name = name;
        this.food = food;
    }

    /**
     * jdk 默认会把当前实例传递到非静态方法中。参数名为this，默认位置是第一个
     * @param num
     * @return
     */
    public int eat(Integer num) {
        System.out.println("吃饭：" + num + "斤");
        this.food -= num;
        return food;
    }

    public static void bark(Dog dog) {
        System.out.println(Dog.class + ": 调用 bark 方法");
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", food=" + food +
                '}';
    }
}