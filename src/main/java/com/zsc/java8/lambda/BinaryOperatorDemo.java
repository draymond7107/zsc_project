package com.zsc.java8.lambda;

import org.junit.Test;

import java.util.Optional;
import java.util.function.BinaryOperator;

/**
 *  二目算符 接收两个T对象，返回T对象
 *
 * @Auther: ZhangSuchao
 * @Date: 2019/10/9 12:23
 */
public class BinaryOperatorDemo {

    @Test
    public void binaryOperator() {
        BinaryOperator<String> binaryOperator = (m, n) -> String.valueOf(Integer.parseInt(m) + Integer.parseInt(n));
        String apply = binaryOperator.apply("1", "3");
        Optional.ofNullable(apply).ifPresent(System.out::println);
    }


}