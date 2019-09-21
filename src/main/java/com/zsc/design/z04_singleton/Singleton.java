package com.zsc.design.z04_singleton;


/**
 * @author ZhangSuchao
 * @create 2019/9/9
 * @since 1.0.0
 */

public class Singleton {

}

/**
 * 1: 饿汉式(线程安全，调用效率高，但是不能延时加载)
 */
class Singleton1 {

    private static Singleton1 singleton = new Singleton1();

    // 1 构造器私有
    private Singleton1() {
    }

    //对外提供获取对象接口
    public static Singleton1 getInstance() {
        return singleton;
    }
}

/**
 * 2：懒汉式延时加载方式 （(线程安全，调用效率不高，但是能延时加载）
 */
class Singleton2 {
    //类初始化时，不初始化这个对象(延时加载，真正用的时候再创建)
    private static Singleton2 singleton;

    // 1 构造器私有
    private Singleton2() {
    }

    //同步方法，效率低
    public static synchronized Singleton2 getInstance() {
        if (singleton == null) {
            singleton = new Singleton2();
        }
        return singleton;
    }
}

/**
 * 内部类方式
 */
class Singleton3 {

    // 1私有构造器
    private Singleton3() {
    }

    private static class SingleClassInstance {
        private static final Singleton3 singleton3 = new Singleton3();
    }

    public static Singleton3 getInstance() {
        return SingleClassInstance.singleton3;
    }
}