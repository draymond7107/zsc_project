package com.zsc.java8.lambda;

import org.junit.Test;

import java.util.function.Function;

// 一个输入与一个输出
public class FunctionDemo {

    @Test
    public void test1() {

        // #1 第一个参数，参数类型， 第二个参数：返回值类型
        Function<Integer, Integer> function = s -> s * s;
        Integer apply = function.apply(12);
        System.out.println(apply);  //  144

    }

    @Test
    public void test2() {
        // #1 输入 String 返回 int 的function
        Function<String, Integer> parseInt = Integer::parseInt;
        Integer apply1 = parseInt.apply("12");
        System.out.println(apply1);  // 12
    }

    @Test
    public void test3() {

        // #1  compose   ()里面的先执行

        Function<Integer, Integer> function1 = s -> s * s;
        Function<Integer, Integer> function2 = s -> --s;
        Function<Integer, Integer> function3 = s -> ++s;

        // 先执行function3 (3) 再执行 function2 (2) 再执行function1 (4)  先执行的结果输出作为后执行的输入
        Integer apply = function1.compose(function2).compose(function3).apply(2);
        System.out.println(apply);  //4
    }

    @Test
    public void test4() {
        Function<Integer, Integer> function1 = s -> s * s;
        Function<Integer, Integer> function2 = s -> --s;
        Function<Integer, Integer> function3 = s -> s = s + 2;

        // 先执行 function1 (9) 再执行function2(8) 再执行function3 (10)
        Integer apply = function1.andThen(function2).andThen(function3).apply(3);
        System.out.println(apply);
    }

    @Test
    public void test5() {
        Function<Integer, Integer> function1 = s -> s * s;
        Function<Integer, Integer> function2 = s -> --s;
        Function<Integer, Integer> function3 = s -> s = s + 2;

        // 先执行 function1 (9) 再执行function2(8) 再执行function3 (10)
        Integer apply = function1.andThen(function2.andThen(function3)).apply(3);
        System.out.println(apply);  // 10
    }

    @Test
    public void test6() {
        // identity方法会返回一个不进行任何处理的Function，即输出与输入值相等；
        Object s = Function.identity().apply("s");
        System.out.println(s.toString());   // s
    }


}
