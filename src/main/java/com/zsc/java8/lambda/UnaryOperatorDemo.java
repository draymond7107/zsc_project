package com.zsc.java8.lambda;

import org.junit.Test;

import java.util.Optional;
import java.util.function.UnaryOperator;

/**
 * 1元运算
 * 接收T对象，返回T对象
 *
 * @Auther: ZhangSuchao
 * @Date: 2019/10/9 12:18
 */
public class UnaryOperatorDemo {

    @Test
    public void unaryOperator() {
        UnaryOperator<Integer> unaryOperator = (s) -> s + 1;
        Integer apply = unaryOperator.apply(1);
        Optional.ofNullable(apply).ifPresent(System.out::println); // 2

    }
}