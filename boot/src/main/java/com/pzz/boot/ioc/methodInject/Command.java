package com.pzz.boot.ioc.methodInject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

/**
 * @program: grandle
 * @description:
 * @author: pzz
 * @create: 2020-12-29 09:26
 **/
public class Command {

//    @Autowired
    public void print(){
        System.out.println(this);
    }
}
