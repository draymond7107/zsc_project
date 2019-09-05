package com.zsc.java8.functionalInterface;

import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.Arrays;

@FunctionalInterface
public interface MethodRef {
    void test(String s);
}

@FunctionalInterface
interface MethodRef1 {
    void test(int[] arr);
}

@FunctionalInterface
interface MethodRef3 {
    void test(PrintStream printStream, String s);
}


class TestMethodRef {

    public static void main(String[] args) {
        MethodRef methodRef = (s) -> {
            System.out.println("s==" + s);
        };
        methodRef.test("字符串的");

        //引用实例方法
        MethodRef methodRef1 = System.out::println;
        methodRef1.test("实例方法引用");

        //引用类的静态static方法
        MethodRef1 methodRef11 = Arrays::sort;
        int[] arr = {1, 3, 5, 2, 55, 2};
        methodRef11.test(arr);
        System.out.println(Arrays.toString(arr));

        //引用类的实例方法
        MethodRef3 methodRef3 = PrintStream::println;
        methodRef3.test(System.out, "引用类的实例方法");

        //普通形式
        MethodRef3 methodRef4 = (printStream, s) -> {
            printStream.println(s);
        };
        methodRef4.test(System.out,"引用类的实例方法");

    }
}
