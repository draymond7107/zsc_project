package com.zsc.java8.stream;


import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ZhangSuchao
 * @create 2019/9/20
 * @since 1.0.0
 */

public class StreamDemo1 {

    // #1 foreach
    @Test
    public void test1() {

        Stream<String> stream = Stream.of("I", "LOVE", "YOU", "TOO");
        Consumer consumer = s -> System.out.println(s);
        stream.forEach(consumer);

    }

    // #2 filter  过滤集合中的元素的长度为3的
    @Test
    public void test2() {
        Stream<String> stream = Stream.of("I", "LOVE", "YOU", "TOO");
        Predicate<String> predicate = (s) -> {
            return s.length() == 3;
        };
        //  Predicate<String> predicate=s -> s.length()==3;  简写

        stream.filter(predicate).forEach(s -> System.out.println(s));
    }


    // #3 map
    @Test
    public void test3() {
        Stream<String> stream = Stream.of("I", "LOVE", "YOU", "TOO");
        Function<String, String> function = (s) -> {
            return s.toLowerCase();
        };

        //  Function<String, String> function1 = s -> s.toLowerCase();  简写
        stream.map(function).forEach(s -> System.out.println(s));
    }


    // #4 flatMap
    @Test
    public void test4() {
        Stream<List<Integer>> stream = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4, 5));
        stream.flatMap(list -> list.stream())
                .forEach(s -> System.out.println(s));
    }

    // #5 collect toList
    @Test
    public void test5() {
        Stream<String> stream = Stream.of("I", "LOVE", "YOU", "TOO");
        List<String> collect = stream.collect(Collectors.toList());
        collect.forEach(item -> System.out.println(item));
        collect.stream().forEach(s -> System.out.println(s));
    }

    // #6 collect toSet
    @Test
    public void test6() {
        Stream<String> stream = Stream.of("I", "LOVE", "YOU", "TOO");
        Set<String> collect = stream.collect(Collectors.toSet());
        collect.forEach(s -> System.out.println(s));
    }

    // #6 collect toMap
    @Test
    public void test7() {
        Stream<String> stream = Stream.of("I", "LOVE", "YOU", "TOO");
        Map<String, Integer> map = stream.collect(Collectors.toMap(Function.identity(), String::length)); // (3)
        Set<String> keySet = map.keySet();
        keySet.forEach(s -> {
            Integer integer = map.get(s);
            System.out.println(s + " : " + integer);
        });

    }

    @Test
    public void test8() {

    }

    @Test
    public void test9() {

    }

    @Test
    public void test10() {

    }

}
