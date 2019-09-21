package com.zsc.java8.wangwenjun;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ZhangSuchao
 * @create 2019/9/21
 * @since 1.0.0
 */

public class SimpleStream {


    //要求 菜单里的卡路里 低于400的菜的名字，按照从小到大排序
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

    // 使用stream完成筛选，
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

    @Test
    public void test3() {

    }

    @Test
    public void test4() {

    }

    @Test
    public void test5() {

    }
}
