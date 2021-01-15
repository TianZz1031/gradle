package com.pzz.designPattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @program: grandle
 * @description:动态代理
 * @author: pzz
 * @create: 2021-01-12 09:23
 **/
interface IHelloService {
    String sayHello(String userName);
    String sayByeBye(String userName);
}
class HelloService implements IHelloService {
    @Override
    public String sayHello(String userName) {
        System.out.println(userName + " hello");
        return userName + " hello";
    }
    @Override
    public String sayByeBye(String userName) {
        System.out.println(userName + " ByeBye");
        return userName + " ByeBye";
    }
}
// 中间类
class JavaProxyInvocationHandler implements InvocationHandler {
    /**
     * 中间类持有委托类对象的引用,这里会构成一种静态代理关系
     */
    private Object obj ;
    public JavaProxyInvocationHandler(Object obj){
        this.obj = obj;
    }
    public Object newProxyInstance() {
        return Proxy.newProxyInstance(
                //使用真实对象的类加载器
                obj.getClass().getClassLoader(),
                //使用真实对象实现的接口，可以同时指定多个接口
                obj.getClass().getInterfaces(),
                //方法调用的实际处理者，代理对象的方法调用都会转发到这里
                this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("invoke before");
        Object result = method.invoke(obj, args);
        System.out.println("invoke after");
        return result;
    }
}
public class JDKProxy {
    public static void main(String[] args) {
        JavaProxyInvocationHandler proxyInvocationHandler = new JavaProxyInvocationHandler(new HelloService());
        IHelloService helloService = (IHelloService) proxyInvocationHandler.newProxyInstance();
        helloService.sayByeBye("paopao");
        helloService.sayHello("yupao");
    }
}