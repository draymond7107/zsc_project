package com.zsc.java8.wangwenjun;


import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
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

    // #3 filter 过滤
    @Test
    public void test3() {

        List<Dish> dishList = DishList.getDishList();
        Predicate<Dish> filterPredicate = (dish) -> dish.getCalories() < 500;
        //   Collector
        List<Dish> collect = dishList.stream().filter(filterPredicate).collect(Collectors.toList());
        for (Dish dish : collect) {
            System.out.println(dish);
        }
    }

    //  #4 limit
    @Test
    public void test4() {
        List<Dish> dishList = DishList.getDishList();
        List<Dish> collect = dishList.stream().limit(3).collect(Collectors.toList());
        collect.stream().forEach(System.out::println);
    }

    // #5 skip
    @Test
    public void test5() {
        List<Dish> dishList = DishList.getDishList();
        dishList.stream().skip(3).forEach(System.out::println);
    }

    // #6 map
    @Test
    public void test6() {
        List<Dish> dishList = DishList.getDishList();
        Function<Dish, String> function = (dish) -> dish.getName();
        List<String> collect = dishList.stream().map(function).collect(Collectors.toList());
        collect.stream().forEach(System.out::println);
    }

    // #7 flatMap
    @Test
    public void test7() {
        String[] words = {"Hello", "World"};
        //
        Stream<String[]> stream = Arrays.stream(words).map(w -> w.split(""));  //  Stream<String[]>
        //    stream.flatMap()

    }

    // #8 flatMap
    @Test
    public void test8() {

    }

    // #9 flatMap
    @Test
    public void test9() {

    }

    // #10 flatMap
    @Test
    public void test10() {

    }

    public static void main(String[] args) {
        List<User> userList = new ArrayList<>();


        User user1 = new User();
        user1.setId(1);
        user1.setRealName("张三");
        userList.add(user1);

        User user2 = new User();
        user2.setId(2);
        user2.setRealName("张三");
        userList.add(user2);

        User user3 = new User();
        user3.setId(3);
        user3.setRealName("王武");
        userList.add(user3);

        User user4 = new User();
        user4.setId(4);
        user4.setRealName("王武伟");
        userList.add(user4);

        //找出name相同，id不同的用户
        List<User> uniqueUserList = new ArrayList<>();

        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            User existUser = isExistName(uniqueUserList, user);
            if (existUser != null) {
                uniqueUserList.remove(existUser);
            } else {
                uniqueUserList.add(user);
            }
        }


        uniqueUserList.stream().forEach(System.out::println);
    }

    //返回已经存在的用户的id
    private static User isExistName(List<User> uniqueUserList, User user) {
        if (uniqueUserList.size() == 0) return null;
        for (User user1 : uniqueUserList) {
            String realName = user1.getRealName();
            if (user.getRealName().equals(realName)) {
                return user1;
            }
        }
        return null;
    }

}
