package com.zsc.java8.lambda;

import org.junit.Test;

import java.util.function.Predicate;

//predicate的中文意思是“断定”，即判断的意思，判断某个东西是否满足某种条件
public class PredicateDemo {

    // test
    @Test
    public void test1() {

        // 传入一个参数，返回boolean
        Predicate<String> predicate = (s) -> {
            return s.endsWith("a");
        };
        //简写   Predicate<String>  predicate1=s->s.endsWith("a");
        boolean b = predicate.test("asd");
        System.out.println(b);
    }


    // negate  结果取反
    @Test
    public void test2() {


        Predicate<String> predicate = s -> s.endsWith("a");
        boolean b = predicate.test("asd");
        System.out.println(b);  //false

        boolean asd = predicate.negate().test("asd");
        System.out.println(asd);    //true
    }

    // and  针对同一输入值，多个Predicate均返回True时返回True，否则返回False；
    @Test
    public void test3() {

        Predicate<String> predicate1 = s -> s.endsWith("a");
        Predicate<String> predicate2 = s -> s.startsWith("a");

        boolean asa = predicate1.and(predicate2).test("asa");
        System.out.println(asa); //true
        boolean ass = predicate1.and(predicate2).test("ass");
        System.out.println(ass); //false
    }
    // or  针对同一输入值，多个Predicate只要有一个返回True则返回True，否则返回False

    @Test
    public void test4() {
        Predicate<String> predicate1 = s -> s.endsWith("a");
        Predicate<String> predicate2 = s -> s.startsWith("a");

        boolean asa = predicate1.or(predicate2).test("asa");
        System.out.println(asa); //true
        boolean ass = predicate1.or(predicate2).test("ass");
        System.out.println(ass); //true
        boolean sass = predicate1.or(predicate2).test("sass");
        System.out.println(sass); //false
    }

}
