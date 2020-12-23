package com.pzz.mybatis.beans;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @program: grandle
 * @description: 测试mybatis
 * @author: pzz
 * @create: 2020-12-15 17:03
 **/
public class User {
    private String id;
    private String uname;
    private int age;
    private List<User> sons;
    private  User partner;
    private  User parent;

    public User() { }
    public User(String id, String uname, int age) {
        this.id = id;
        this.uname = uname;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<User> getSons() {
        return sons;
    }

    public void setSons(List<User> sons) {
        this.sons = sons;
    }

    public User getPartner() {
        return partner;
    }

    public void setPartner(User partner) {
        this.partner = partner;
    }

    public User getParent() {
        return parent;
    }

    public void setParent(User parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", uname='" + uname + '\'' +
                ", age=" + age +
                ", sons=" + Arrays.toString(sons != null ? sons.toArray() : null) +
                ", partner=" + partner +
                ", parent=" + parent +
                '}';
    }
}
