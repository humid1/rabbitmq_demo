package com.humid.client.entity;

/**
 * @author qiujianping
 * @date Created in 2021/8/13 16:16
 */
public class User {
    private String id;

    /**
     * 姓名
     *  指定不能为空
     */
    private String name;

    /**
     * 年龄
     *  指定范围是 1 - 100
     */
    private int age;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
