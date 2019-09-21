package com.zsc.java8.lambda;

import org.junit.Test;

import java.util.function.Consumer;

//Consumer 有输入，没有输出
public class ConsumerDeomo {

    @Test
    public void test1() {

        // #1 函数推导
        Consumer consumer1 = (s) -> System.out.println("三娃子");
        consumer1.accept("你好");
        // #2 第二种写法
        Consumer consumer2=System.out::println;
        consumer2.accept("洋洋");

        // #3 andThen 从前向后执行
        consumer2.andThen(consumer2).andThen(consumer1).accept("二哈");  //二哈 二哈 三娃子

        // consumer2执行，打印"二哈"
        //consumer2执行，打印"二哈"
        //consumer1执行，打印"三娃子" （consumer1的接口打印的是"三娃子"）

    }
}
