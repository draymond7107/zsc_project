package com.zsc.java8.functionalInterface;

//接口可以写“非抽象方法”
@FunctionalInterface
public interface InterfaceDemo1 {
    //抽象方法
    void sysHello();

    //static修饰的静态非抽象方法
    static String sysStatic() {
        return "sysStatic";
    }

    //default 修饰的非抽象方法
    default String sysDefault() {
        return "sysDefault";
    }
}

class InterfaceDemoExtence implements InterfaceDemo1 {
    @Override
    public void sysHello() {
        System.out.println("hello");
    }

    @Override
    public String sysDefault() {
        return "sysImplementDefault";
    }

    /*@Override //静态方法不能被重写
    public static String sysStatic() {
        return "sysStatic";
    }*/

    public static void main(String[] args) {
        InterfaceDemo1 interfaceDemo = new InterfaceDemoExtence();
        //调用抽象方法
        interfaceDemo.sysHello();
        //调用default方法
        String s = interfaceDemo.sysDefault();
        System.out.println(s);
        //调用static方法
        String s1 = InterfaceDemo1.sysStatic();
        System.out.println(s1);
    }


}