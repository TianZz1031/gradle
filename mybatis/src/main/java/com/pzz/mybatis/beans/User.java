package com.pzz.mybatis.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: grandle
 * @description: 测试mybatis
 * @author: pzz
 * @create: 2020-12-15 17:03
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    private String id;
    private String uname;
    private int age;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", uname='" + uname + '\'' +
                ", age=" + age +
                '}';
    }
}
