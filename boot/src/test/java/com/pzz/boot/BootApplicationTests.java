package com.pzz.boot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootTest
class BootApplicationTests {

    @Test
    void contextLoads() {
        ApplicationContext ac = new ClassPathXmlApplicationContext();
    }

}
