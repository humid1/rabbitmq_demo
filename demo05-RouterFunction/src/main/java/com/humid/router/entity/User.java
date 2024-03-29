package com.humid.router.entity;

import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

/**
 * @author qiujianping
 * @date Created in 2021/8/13 16:16
 */
@Document(collection = "user")
public class User {
    @Id
    private String id;

    /**
     * 姓名
     *  指定不能为空
     */
    @NotBlank
    private String name;

    /**
     * 年龄
     *  指定范围是 1 - 100
     */
    @Range(min = 1, max = 100)
    private int age;

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
