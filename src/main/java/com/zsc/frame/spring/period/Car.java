package com.zsc.frame.spring.period;


/**
 * @author ZhangSuchao
 * @create 2019/8/27
 * @since 1.0.0
 */
// #2.2.1
public class Car {

    public Car() {
        System.out.println("car constructor.....");
    }

    public void init() {
        System.out.println("car init.....");
    }

    public void destroy() {
        System.out.println("car destory.....");
    }

}
