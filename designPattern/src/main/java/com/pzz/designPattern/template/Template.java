package com.pzz.designPattern.template;

/**
 * @program: grandle
 * @description: 模板设计模式
 * @author: pzz
 * @create: 2021-01-12 10:27
 **/
abstract class PutIntoFridge {
    //复杂的业务逻辑
    public final void execute(){
        open(); put();  close();
    }
    //重用的业务步骤
    private void open(){    System.out.println("打开冰箱"); }
    private void close(){   System.out.println("关闭冰箱"); }
    //暴露给子类重写的钩子函数
    protected abstract void put();
}
class Pork extends PutIntoFridge{
    @Override
    protected void put() {
        System.out.println("猪肉放进冰箱");
    }
}
public class Template {
    public static void main(String[] args) {
        PutIntoFridge put = new Pork();
        put.execute();
    }
}
