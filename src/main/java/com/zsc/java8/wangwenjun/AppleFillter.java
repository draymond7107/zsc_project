package com.zsc.java8.wangwenjun;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppleFillter {

    //使用策略模式
    public interface AppleFilter {
        boolean filter(Apple apple);
    }


    public static void main(String[] args) {
        List<Apple> apples = Arrays.asList(new Apple("red", 150), new Apple("blue", 100), new Apple("green", 200));

        //  apples.add(new Apple("红色", 100)); java.lang.UnsupportedOperationException
        AppleFillter fillterApple = new AppleFillter();
        List<Apple> greenApples = fillterApple.findGreenApples(apples);
        assert greenApples.size() == 2;
        for (Apple apple :
                greenApples) {
            System.out.println(apple.toString());
        }
        //##### 1 使用策略+内部类 ######
        //策略模式：也可以先写好实现类，方法传实现类
        AppleFilter appleFilter = new AppleFilter() {
            @Override
            public boolean filter(Apple apple) {
                if ("red".equals(apple.getColor()) && apple.getWeight() >= 100) return true;
                return false;
            }
        };
        List<Apple> appleList = fillterApple.filterAppls(appleFilter, apples);
        for (Apple apple :
                appleList) {
            System.out.println(apple.toString());
        }
        //##### 1 使用策略+lambda ######
        List<Apple> appleLambda = fillterApple.filterAppls((apple) -> {
            if ("blue".equals(apple.getColor()) && apple.getWeight() >= 100) return true;
            return false;
        }, apples);
        for (Apple apple : appleLambda) {
            System.out.println(apple.toString());
        }

    }

    //筛选绿色的苹果
    public List<Apple> findGreenApples(List<Apple> apples) {
        List<Apple> appleList = new ArrayList<>();
        for (Apple apple : apples) {
            if ("green".equals(apple.getColor())) {
                appleList.add(apple);
            }
        }
        return appleList;
    }
    //筛选红色的苹果，怎么办；；再根据重量+颜色  筛选怎么办？？？数据不变 ，条件经常变化的方法怎么兼容

    //##### 1 使用策略 ######

    public List<Apple> filterAppls(AppleFilter filter, List<Apple> apples) {
        List<Apple> appleList = new ArrayList<>();
        for (Apple apple : apples) {
            if (filter.filter(apple)) appleList.add(apple); //filter的实现靠实现类，或者匿名内部类
        }
        return appleList;
    }


}
