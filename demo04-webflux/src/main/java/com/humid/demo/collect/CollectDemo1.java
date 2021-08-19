package com.humid.demo.collect;

import org.apache.commons.collections.MapUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 收集器
 * @author qiujianping
 * @date Created in 2021/8/12 15:43
 */
public class CollectDemo1 {

    public static void main(String[] args) {
        List<Student> studentList = Arrays.asList(
            new Student("用户1", 10, Gender.FEMALE, Grade.ONE),
            new Student("用户2", 19, Gender.FEMALE, Grade.ONE),
            new Student("用户3", 15, Gender.FEMALE, Grade.THREE),
            new Student("用户4", 11, Gender.MALE, Grade.ONE),
            new Student("用户5", 17, Gender.MALE, Grade.TWO),
            new Student("用户6", 14, Gender.FEMALE, Grade.ONE),
            new Student("用户7", 20, Gender.MALE, Grade.FORE)
        );

        // 使用 s -> s.getAge()  ==> Student::getAge, 不会多生成一个类似 lambda$0 这样的函数
        List<Integer> collect = studentList.stream().map(Student::getAge).collect(Collectors.toList());
        Set<Integer> collect1 = studentList.stream().map(Student::getAge).collect(Collectors.toSet());
        Set<Integer> collect2 = studentList.stream().map(Student::getAge).collect(Collectors.toCollection(TreeSet::new));
        System.out.println("所有用的年龄集：" + collect);
        System.out.println("所有用的年龄集：" + collect1);
        System.out.println("所有用的年龄集：" + collect2);

        // 统计汇总信息
        IntSummaryStatistics summaryStatistics = studentList.stream().collect(Collectors.summarizingInt(Student::getAge));
        System.out.println("所有学生年龄集（和、最大值、最小值、平均值）：" + summaryStatistics);

        // 分块
        Map<Boolean, List<Student>> listMap = studentList.stream().collect(Collectors.partitioningBy(s -> s.getSex() == Gender.MALE));
        MapUtils.verbosePrint(System.out, "男女学生列表：", listMap);

        // 分组 (根据年级进行分组)
        Map<Grade, List<Student>> collect3 = studentList.stream().collect(Collectors.groupingBy(Student::getGrade));
        MapUtils.verbosePrint(System.out, "年级学生列表：", collect3);

        // 等到班级所有的学生个数
        Map<Grade, Long> collect4 = studentList.stream().collect(Collectors.groupingBy(Student::getGrade, Collectors.counting()));
        MapUtils.verbosePrint(System.out, "年级学生个数列表：", collect4);
    }
}

class Student {

    private String name;

    private Integer age;

    private Gender sex;

    private Grade grade;

    public Student() {

    }

    public Student(String name, Integer age, Gender sex, Grade grade) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Enum<Gender> getSex() {
        return sex;
    }

    public void setSex(Gender sex) {
        this.sex = sex;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", grade=" + grade +
                '}';
    }
}

enum Gender {
    FEMALE("女"),MALE("男");

    Gender() {

    }

    Gender(String name) {

    }
}

enum Grade {
    ONE,
    TWO,
    THREE,
    FORE
}