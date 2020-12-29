package com.pzz.boot.services;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * @program: grandle
 * @description:
 * @author: pzz
 * @create: 2020-12-26 15:56
 **/
public class Print {
//    @Resource
    private String name;
    public void print(){
        System.out.println("hello world! " + name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
