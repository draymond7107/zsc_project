package com.zsc.java8.lambda;

import org.junit.Test;

import java.util.Optional;

/**
 * @Auther: ZhangSuchao
 * @Date: 2019/10/18 08:32
 */
public class IntFunction {
    @Test
    public void intFunction(){
        java.util.function.IntFunction<String> intFunction =(s)->String.valueOf(s+4);
        String apply = intFunction.apply(5);
        Optional.ofNullable(apply).ifPresent(System.out::println);

    }
}