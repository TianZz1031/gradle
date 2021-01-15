package com.pzz.boot.ioc.javaConfig;

import com.pzz.boot.services.Print;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Arrays;

/**
 * @program: grandle
 * @description:
 * @author: pzz
 * @create: 2021-01-06 10:00
 **/

public class Main {
    @Autowired
    static Environment env;

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class);


        System.out.println(ctx.getEnvironment().getProperty("Print.name"));
    }

}
