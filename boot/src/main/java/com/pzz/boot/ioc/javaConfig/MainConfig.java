package com.pzz.boot.ioc.javaConfig;

import com.pzz.boot.services.Print;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

import java.lang.reflect.Member;

/**
 * @program: grandle
 * @description:
 * @author: pzz
 * @create: 2021-01-05 14:17
 **/
@Configuration
@ComponentScan("com.pzz.boot")
@PropertySource("p.properties")
public class MainConfig {
    @Autowired
    static Environment env;
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class);
        User user = (User) ctx.getBean("user2");
        user.hi();
    }

    @Bean
    @Qualifier("user1")
    public User user1() {
        return new User("user1",1);
    }
    @Bean
    @Qualifier("user2")
    public User user2(
            @Value("#{user3.name}")String name,User user1
    ) {
        User user2 = new User("user2", 2);
        user2.setName(user1.getName());
        return user2;
    }
    @Bean
    @Qualifier("user3")
    public User user3(InjectionPoint injectionPoint) {
        System.out.println(injectionPoint.getMember());
        return new User("user3",3);
    }

}
