package com.zsc.java8.stream;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class MethodReference {

    public static void main(String[] args) {
        // 1
        Consumer<String> consumer = (s) -> System.out.println(s);
        useConsumer(consumer, "Hello Alex");

        // 2
        useConsumer((s) -> System.out.println(s), "Hello zsc");

        // 3
        useConsumer(System.out::println, "Hello ZSC");


        Function<String, Integer> ref = Integer::parseInt;
        Integer apply = ref.apply("1f3");
        System.out.println(apply);
    }

    private static <T> void useConsumer(Consumer<T> consumer, T t) {
        consumer.accept(t);
        consumer.accept(t);
    }

}

class StreamDemo{

    public static void main(String[] args) {

        List newList = DataUtils.newList();
        List oldList = DataUtils.oldList();
        Predicate predicate=(s)->oldList.contains(s);

        Stream stream = newList.stream().filter(predicate);


    }
}