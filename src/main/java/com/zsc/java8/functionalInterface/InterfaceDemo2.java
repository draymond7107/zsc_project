package com.zsc.java8.functionalInterface;

public interface InterfaceDemo2 {
    static int staticPara = 2;

    default void sysDefault() {
        System.out.println("InterfaceDemo2的Default方法");
    }

    static void sysStatic() {
        System.out.println("InterfaceDemo2的Static方法");
    }
}

interface InterfaceDemo3 {
    default void sysDefault() {
        System.out.println("InterfaceDemo3的Default方法");
    }

    default void sysDefault1() {
        System.out.println("InterfaceDemo3的Default方法");
    }
}

class InterfaceTest1 implements InterfaceDemo2, InterfaceDemo3 {
    //有相同的default需要被重写
    @Override
    public void sysDefault() {
        InterfaceDemo2.super.sysDefault();  //实现的接口有相同的方法，则可以使用此方式指定调用某一个接口的方法
    }

    public static void main(String[] args) {
        InterfaceTest1 interfaceTest1 = new InterfaceTest1();
        interfaceTest1.sysDefault();
        int i = InterfaceTest1.staticPara;  //static属性会被继承/实现
        System.out.println(i);

        InterfaceDemo3 interfaceDemo3 = new InterfaceDemo3() {
        };
        interfaceDemo3.sysDefault();
        //    InterfaceTest1.sysStatic();   //报错：static方法不能被继承/实现

    }
}

