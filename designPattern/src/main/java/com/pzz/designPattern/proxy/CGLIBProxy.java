package com.pzz.designPattern.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @program: grandle
 * @description: cglib动态代理
 * @author: pzz
 * @create: 2021-01-12 09:41
 **/
// 委托类,是一个简单类
class Hello {
    public String sayHello(String userName){
        System.out.println("目标对象的方法执行了");
        return userName + " sayHello";
    }
    public String sayByeBye(String userName){
        System.out.println("目标对象的方法执行了");
        return userName + " sayByeBye";
    }
}
//CglibInterceptor 用于对方法调用拦截以及回调
class CglibInterceptor implements MethodInterceptor {
    //CGLIB 增强类对象，代理类对象是由 Enhancer 类创建的，Enhancer 是 CGLIB 的字节码增强器，可以很方便的对类进行拓展
    private Enhancer enhancer = new Enhancer();

    //cglib生成用来代替Method对象的一个对象，使用MethodProxy比调用JDK自身的Method直接执行方法效率会有提升
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("方法调用之前");
        Object o = proxy.invokeSuper(obj, args);
        System.out.println("方法调用之后");
        return o;
    }

    public  Object newProxyInstance(Class<?> c) {
        //指定代理对象父类
        enhancer.setSuperclass(c);
        //定义代理逻辑对象为当前对象，要求当前对象实现 MethodInterceptor 接口
        enhancer.setCallback(this);
        //使用默认无参数的构造函数创建目标对象,这是一个前提,被代理的类要提供无参构造方法
        return enhancer.create();
    }
}
public class CGLIBProxy {
    public static void main(String[] args) {
        CglibInterceptor cglibProxy = new CglibInterceptor();
        Hello hello = (Hello) cglibProxy.newProxyInstance(Hello.class);
        hello.sayHello("isole");
        hello.sayByeBye("sss");
    }
}
