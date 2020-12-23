package com.pzz.designPattern.factory;

/**
 * @program: grandle
 * @description:
 * @author: pzz
 * @create: 2020-12-23 14:29
 **/
public abstract class AbstractFactory {
    public abstract Color getColor(String color);
    public abstract Shape getShape(String shape) ;
}
