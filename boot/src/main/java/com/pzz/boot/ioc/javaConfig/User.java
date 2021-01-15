package com.pzz.boot.ioc.javaConfig;

/**
 * @program: grandle
 * @description:
 * @author: pzz
 * @create: 2021-01-06 09:57
 **/
public class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;

    }
    public void hi(){
        System.out.println("hi " + age + " years old " + name);
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
}
