package com.pzz.boot.ioc;

import com.pzz.boot.ioc.methodInject.Command;
import com.pzz.boot.ioc.methodInject.Commandmannager;
import com.pzz.boot.services.Print;
import org.springframework.beans.factory.groovy.GroovyBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericGroovyApplicationContext;

/**
 * groovy 配置bean测试
 *
 * @program: grandle
 * @description:
 * @author: pzz
 * @create: 2020-12-26 15:13
 **/
public class GroovyIOC {
    private String s = "hello world!";
    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        new XmlBeanDefinitionReader(context).loadBeanDefinitions("beans.xml");
        new GroovyBeanDefinitionReader(context).loadBeanDefinitions("services.groovy");
        context.refresh();

//        Command command = (Command) context.getBean("myCommand");
        Commandmannager commandmannager = (Commandmannager) context.getBean("commandmannager");
        commandmannager.process();
        commandmannager.process();
        commandmannager.process();


    }

    private static void groovyCombineXML(GenericApplicationContext context) {

        Print print = (Print) context.getBean("print");
        print.print();
        XMLIOCStudy bean = (XMLIOCStudy) context.getBean("xmlioc");
        bean.print();
        GroovyIOC bean1 = (GroovyIOC) context.getBean("groovyIOC");
        bean1.print();
    }

    private static void simpleGroovy() {
        ApplicationContext context = new GenericGroovyApplicationContext("services.groovy");
        XMLIOCStudy bean = (XMLIOCStudy) context.getBean("xmlioc");
        bean.print();
        GroovyIOC bean1 = (GroovyIOC) context.getBean("groovyIOC");
        bean1.print();
    }

    public void print(){
        System.out.println(this.s);
    }



    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }
}
