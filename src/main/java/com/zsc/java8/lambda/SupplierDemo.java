package com.zsc.java8.lambda;

import org.junit.Test;

import java.util.function.Supplier;

/**
 * 提供T对象（例如工厂），不接收值
 * @Auther: ZhangSuchao
 * @Date: 2019/10/9 12:10
 */
public class SupplierDemo {

    @Test
    public void t1() {
        // Supplier 供应者
        Supplier supplier = String::new;

    }

}