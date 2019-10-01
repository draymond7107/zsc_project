package com.zsc.java8.wangwenjun;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class CreatStream {

    // #1 list2Stream
    @Test
    public void test1() {
        List<String> list = Arrays.asList("1", "2");
        Stream<String> stream = list.stream();
    }

    // #2 of
    @Test
    public void test2() {
        Stream<String> stream = Stream.of("1", "2");
    }

    // #3 Arrays.stream
    @Test
    public void test3() {
        Arrays.stream(new String[]{"1", "2"});
    }

    // #4  Files.lines  io流
    @Test
    public void test4() throws Exception {
        Path path = Paths.get("C:\\Users\\86131\\Desktop\\java8\\java8\\wangwenjun\\Dish.java");
        Stream<String> stream = Files.lines(path);
        stream.forEach(System.out::println);
    }

    // #5 Stream.iterate
    @Test
    public void test5() {
        // 产生无限的stream
        Stream<Integer> stream = Stream.iterate(0, n -> n + 2);
        stream.forEach(System.out::println);
    }

    // #6 Stream.generate
    @Test
    public void test6() {
        //  Supplier supplier = () -> Math.random();
        // 产生无限的stream
        Stream<Double> stream = Stream.generate(Math::random).limit(10);
        stream.forEach(System.out::println);
    }

    // #7  自定义返回**对象的Stream
    @Test
    public void test7() {
        Stream<Obj> stream = Stream.generate(new ObjSupplier()).limit(10);
        stream.forEach(System.out::println);
    }


    class ObjSupplier implements Supplier<Obj> {
        private int index = 0;
        private Random random = new Random(System.currentTimeMillis());

        @Override
        public Obj get() {
            index = random.nextInt(100);

            return new Obj(index, "Name->" + index);
        }
    }


    // 自定义返回Stream的对象
    class Obj {

        private int id;

        private String name;

        public Obj() {
        }

        public Obj(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Obj{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
