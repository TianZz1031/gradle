package com.pzz.designPattern.factory;

/**
 * @program: grandle
 * @description: 简单工厂
 * @author: pzz
 * @create: 2021-01-11 17:02
 **/
interface Fruit{
    public void des();
}
interface FruitFactory{
    public Fruit getFruit();
}
class Banana implements Fruit{
    @Override
    public void des() {
        System.out.println("i m a banana");
    }
}
class BananaFactory implements FruitFactory{
    @Override
    public Fruit getFruit() {
        return new Banana();
    }
}
public class SimpleFactory {
    public static void main(String[] args) {
        FruitFactory factory = new BananaFactory();
        Fruit fruit = factory.getFruit();
        fruit.des();
    }
}
