package com.zsc.java8.wangwenjun;


import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 常用的stream操作：filter、map、Collectors.toList\
 * @author ZhangSuchao
 * @create 2019/9/21
 * @since 1.0.0
 */

public class SimpleStream {


    //  ##################### #1 start 要求 菜单里的卡路里 低于400的菜的名字，按照从小到大排序  ##################################
    @Test
    public void getDishNamesByCollections() {

        List<Dish> dishList = DishList.getDishList();
        // #1.1 查找低于400的菜品
        List<Dish> caloriesLess400dishList = new ArrayList<>();

        for (int i = 0; i < dishList.size(); i++) {
            Dish dish = dishList.get(i);
            if (dish.getCalories() < 400) caloriesLess400dishList.add(dish);
        }
        // Comparator.comparing(d -> d.getCalories() 可以替换 (d1, d2) -> Integer.compare(d1.getCalories(), d2.getCalories()))
        // #1.2 排序
        Collections.sort(caloriesLess400dishList, (d1, d2) -> Integer.compare(d1.getCalories(), d2.getCalories()));

        // #1.3 获取名字
        List<String> nameList = new ArrayList<>();
        for (int i = 0; i < caloriesLess400dishList.size(); i++) {
            String name = caloriesLess400dishList.get(i).getName();
            nameList.add(name);
        }
        // #1.3 输出
        nameList.forEach(item -> {
            System.out.println(item);
        });
    }

    // 使用stream完成筛选
    @Test
    public void getDishNamesByStream() {
        List<Dish> dishList = DishList.getDishList();
        List<String> nameList = dishList.stream().filter(dish -> dish.getCalories() < 400).sorted(
                Comparator.comparing(d -> d.getCalories())
        ).map(Dish::getName).collect(Collectors.toList());

        // #1.3 输出
        nameList.forEach(item -> {
            System.out.println(item);
        });
    }
    //  ##################### #1 end 要求 菜单里的卡路里 低于400的菜的名字，按照从小到大排序  ##################################

    // #3 filter 过滤,返回卡路里小于500的
    @Test
    public void filter() {

        List<Dish> dishList = DishList.getDishList();
        Predicate<Dish> filterPredicate = (dish) -> dish.getCalories() < 500;
        //   Collector
        List<Dish> collect = dishList.stream().filter(filterPredicate).collect(Collectors.toList());
        for (Dish dish : collect) {
            System.out.println(dish);
        }
    }

    //  #4 limit 仅仅输出前面3个dish
    @Test
    public void limit() {
        List<Dish> dishList = DishList.getDishList();
        List<Dish> collect = dishList.stream().limit(3).collect(Collectors.toList());
        collect.stream().forEach(System.out::println);
    }

    // #5 skip  跳过前面3个dish输出
    @Test
    public void skip() {
        List<Dish> dishList = DishList.getDishList();
        dishList.stream().skip(3).forEach(System.out::println);
    }

    // #6 toList  取出食物的名字集合
    @Test
    public void toList() {
        List<Dish> dishList = DishList.getDishList();
        Function<Dish, String> function = (dish) -> dish.getName();
        List<String> collect = dishList.stream().map(function).collect(Collectors.toList());
        collect.stream().forEach(System.out::println);
    }

    // #6.2 toSet 取出食物的名字集合
    @Test
    public void toSet() {
        List<Dish> dishList = DishList.getDishList();
        Set<String> collect = dishList.stream().map(Dish::getName).collect(Collectors.toSet());
        Optional.ofNullable(collect).ifPresent(System.out::println); //  [season fruit, chicken, pizza, salmon, beef, pork, rice, french fries, prawns]
    }

    // #6.3 toMap  key 食物的name ：value 食物的卡路里
    @Test
    public void toMap() {
        List<Dish> dishList = DishList.getDishList();
        Map<String, Integer> collect = dishList.stream().collect(Collectors.toMap(Dish::getName, Dish::getCalories));
        Optional.ofNullable(collect).ifPresent(System.out::println);    //{season fruit=120, chicken=400, pizza=550, salmon=450, beef=700, rice=350, pork=800, prawns=300, french fries=530}
    }


    // #7 flatMap  ??
    @Test
    public void flatMap() {
        Stream<List<Integer>> stream = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4, 5));
        stream.flatMap(list -> list.stream())
                .forEach(s -> System.out.println(s));
    }


}
