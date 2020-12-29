package com.pzz.boot.ioc;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

/**
 * @program: grandle
 * @description:
 * @author: pzz
 * @create: 2020-12-25 09:06
 **/
public class XMLIOCStudy {
    public static void main(String[] args) {
//        String s = IOCStudy.class.getClassLoader().getResource("beans.xml").getFile();
//        File file = new File("boot/src/main/resources/beans.xml");
//        System.out.println(file.exists());
//        System.out.println(file.exists());
        BeanFactory beanFactory = new XmlBeanFactory(new FileSystemResource("/D:/pzzWork/grandle/boot/build/resources/main/beans.xml"));
        XMLIOCStudy ioc = (XMLIOCStudy)beanFactory.getBean("ioc");
        ioc.print();
    }

    public void print(){
        System.out.println("yes! i m successed!");
    }
}
