package com.pzz.boot.ioc.methodInject;

import org.springframework.context.annotation.Bean;

/**
 * @program: grandle
 * @description:
 * @author: pzz
 * @create: 2020-12-29 09:26
 **/
public class Command {

    public void print(){
        System.out.println(this);
    }
}
