package com.pzz.designPattern.adapter;

/**
 * @program: grandle
 * @description: 适配器模式
 * @author: pzz
 * @create: 2021-01-15 17:20
 **/
//目标：发动机
interface Motor {   public void drive(); }
//适配者1：电能发动机
class ElectricMotor {
    public void electricDrive() {   System.out.println("电能发动机驱动汽车！"); }
}
//适配者2：光能发动机
class OpticalMotor {
    public void opticalDrive()  {   System.out.println("光能发动机驱动汽车！"); }
}
//电能适配器
class ElectricAdapter implements Motor {
    private ElectricMotor emotor;
    public ElectricAdapter() {  emotor=new ElectricMotor(); }
    public void drive() {   emotor.electricDrive(); }
}
//光能适配器
class OpticalAdapter implements Motor {
    private OpticalMotor omotor;
    public OpticalAdapter() {   omotor=new OpticalMotor(); }
    public void drive() {   omotor.opticalDrive(); }
}
public class AdapterPzz {
    public static void main(String[] args) {
        System.out.println("适配器模式测试：");
        Motor motor= new OpticalAdapter();
        motor.drive();
    }
}
