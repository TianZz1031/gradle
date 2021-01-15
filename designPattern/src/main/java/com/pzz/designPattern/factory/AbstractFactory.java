package com.pzz.designPattern.factory;

/**
 * @program: grandle
 * @description:
 * @author: pzz
 * @create: 2020-12-23 14:29
 **/
interface Computor{
    public void mouse();
    public void keyboard();
    public void screen();
}
class LianxiangComputor implements Computor{
    @Override
    public void mouse() { System.out.println("联想鼠标");}
    @Override
    public void keyboard() { System.out.println("联想键盘");}
    @Override
    public void screen() { System.out.println("联想显示屏");}
}
interface ComputorFactory{
    public Computor getComputor();
}
class LianxiangFactory implements ComputorFactory{
    @Override
    public Computor getComputor() { return new LianxiangComputor();}
}

public abstract class AbstractFactory {
    public static void main(String[] args) {
        ComputorFactory factory = new LianxiangFactory();
        Computor computor = factory.getComputor();
        computor.mouse();
        computor.keyboard();
    }
}
