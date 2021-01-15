package com.pzz.designPattern.proxy;

/**
 * @program: grandle
 * @description: 静态代理
 * @author: pzz
 * @create: 2021-01-12 08:19
 **/
interface Service{
    public void drawback();
}
class ServiceImpl implements Service{
    @Override
    public void drawback() {
        System.out.println("取钱成功");
    }
}
class Proxy implements Service{
    Service service;
    Proxy(){
        service = new ServiceImpl();
    }
    @Override
    public void drawback() {
        service.drawback();
    }
}
public class StaticProxy {
    public static void main(String[] args) {
        Service proxy = new Proxy();
        proxy.drawback();
    }
}
