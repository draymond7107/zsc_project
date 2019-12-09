package com.zsc.java8.wangwenjun;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.function.BinaryOperator;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

public class CollectorStream {

    @Test
    public void test() {

    }

    // averagingDouble  list中某个属性的均值
    @Test
    public void averagingDouble() {
        List<Dish> dishList = DishList.getDishList();
        //ToDoubleFunction 也是一种Function,输入+输出
        ToDoubleFunction<Dish> toDoubleFunction = dish -> dish.getCalories();

        Double caloriesAverage = dishList.stream().collect(Collectors.averagingDouble(Dish::getCalories));
        Optional.ofNullable(caloriesAverage).ifPresent(System.out::println);
    }

    // averagingInt  返回值还是double类型（与averagingDouble的区别）
    @Test
    public void averagingInt() {
        List<Dish> dishList = DishList.getDishList();
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Double collect = integers.stream().collect(Collectors.averagingInt(Integer::intValue));
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }

    // averagingLong
    @Test
    public void averagingLong() {
        List<Dish> dishList = DishList.getDishList();
        Double collect = dishList.stream().collect(Collectors.averagingLong(Dish::getCalories));
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }

    // collectingAndThen  前一个结果出参作为后一个的入参
    @Test
    public void collectingAndThen() {
        List<Dish> dishList = DishList.getDishList();
        //   collectingAndThen(Collector<T,A,R> downstream,Function<R,RR> finisher)

        String collect = dishList.stream().collect(Collectors.collectingAndThen(Collectors.averagingLong(Dish::getCalories), average ->
                "卡路里的平均值为 " + average));
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }

    //  counting
    @Test
    public void counting() {
        List<Dish> dishList = DishList.getDishList();
        Long collect = dishList.stream().collect(Collectors.counting());

        Optional.ofNullable(collect).ifPresent(System.out::println);
    }


    //  unmodifiableList 不能修改
    @Test
    public void unmodifiableList() {
        List<Dish> dishList = DishList.getDishList();
        List<Dish> collect = dishList.stream().collect(Collectors.toList());
        collect.add(new Dish());
        //  List<Dish> collect = dishList.stream().collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));

        //  dishList.add(new Dish());  java.lang.UnsupportedOperationException  因为 DishList.getDishList()使用的内部类，不支持增删

        List<Dish> unmodifiableList = Collections.unmodifiableList(dishList);
        dishList.add(new Dish());


    }

    //  groupingBy
    @Test
    public void groupingBy() {
        List<Dish> dishList = DishList.getDishList();
        Map<Dish.Type, List<Dish>> collect = dishList.stream().collect(Collectors.groupingBy(Dish::getType));
        Optional.of(collect).ifPresent(System.out::println);

    }

    // groupingBy 每种类型的卡路里的平均值
    @Test
    public void groupingByAnd() {
        List<Dish> dishList = DishList.getDishList();

        Map<Dish.Type, Long> collect = dishList.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));
        System.out.println(collect.getClass()); //class java.util.HashMap   // 默认返回HashMap
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }

    //groupingBy 指定返回值类型  根据类型，统计
    @Test
    public void groupingBy3() {
        List<Dish> dishList = DishList.getDishList();
        Map<Dish.Type, Long> collect = dishList.stream().collect(Collectors.groupingBy(Dish::getType, TreeMap::new, Collectors.counting()));
        System.out.println(collect.getClass()); //class java.util.TreeMap   // 返回TreeMap
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }


    // summarizingInt  对**的统计最大值，最小值，平均值，总计
    @Test
    public void summarizingInt() {

        List<Dish> dishList = DishList.getDishList();
        IntSummaryStatistics collect = dishList.stream().collect(Collectors.summarizingInt(Dish::getCalories));
        System.out.println(collect); //  IntSummaryStatistics{count=9, sum=4200, min=120, average=466.666667, max=800}

    }

    //######################################  第二部分的collectors   #######################################

    // groupingByConcurrent
    @Test
    public void groupingByConcurrent() {
        List<Dish> dishList = DishList.getDishList();
        ConcurrentMap<Dish.Type, List<Dish>> collect = dishList.stream().collect(Collectors.groupingByConcurrent(Dish::getType));
        Optional.ofNullable(collect).ifPresent(System.out::println);
        System.out.println(collect.getClass());     // class java.util.concurrent.ConcurrentHashMap
    }

    @Test  // groupingByConcurrent  根据类型统计卡路里平均值
    public void groupingByConcurrent2() {
        List<Dish> dishList = DishList.getDishList();
        ConcurrentMap<Dish.Type, Double> collect = dishList.stream().collect(
                Collectors.groupingByConcurrent(Dish::getType, Collectors.averagingInt(Dish::getCalories)));
        Optional.ofNullable(collect).ifPresent(System.out::println);    //{OTHER=387.5, FISH=375.0, MEAT=633.3333333333334}
        System.out.println(collect.getClass());     // class java.util.concurrent.ConcurrentHashMap
    }


    // 统计，返回类型的卡路里平均值，使用跳表接收
    @Test
    public void groupingByConcurrent3() {
        List<Dish> dishList = DishList.getDishList();
        ConcurrentMap<Dish.Type, Double> collect = dishList.stream().collect(
                Collectors.groupingByConcurrent(Dish::getType, ConcurrentSkipListMap::new, Collectors.averagingInt(Dish::getCalories)));
        Optional.ofNullable(collect).ifPresent(System.out::println);    //{MEAT=633.3333333333334, FISH=375.0, OTHER=387.5}
        System.out.println(collect.getClass());     // class java.util.concurrent.ConcurrentSkipListMap 跳表结构，用空间换时间，查询快

    }

    // joining 拼接（需要元素为为String类型，或者转换为String）
    @Test
    public void joining() {
        List<Dish> dishList = DishList.getDishList();
        String collect = dishList.stream().map(Dish::getName).collect(Collectors.joining());
        Optional.ofNullable(collect).ifPresent(System.out::println);    //porkbeefchickenfrench friesriceseason fruitpizzaprawnssalmon

    }   // joining 拼接（需要元素为为String类型，或者转换为String）

    @Test
    public void joining2() {
        List<Dish> dishList = DishList.getDishList();
        String collect = dishList.stream().map(Dish::getName).collect(Collectors.joining(","));
        Optional.ofNullable(collect).ifPresent(System.out::println);    //porkbeefchickenfrench friesriceseason fruitpizzaprawnssalmon
        //  pork,beef,chicken,french fries,rice,season fruit,pizza,prawns,salmon
    }


    // joining 拼接（需要元素为为String类型，或者转换为String）
    @Test
    public void joining3() {
        List<Dish> dishList = DishList.getDishList();
        String collect = dishList.stream().map(Dish::getName).collect(Collectors.joining(",", "names={", "}"));
        Optional.ofNullable(collect).ifPresent(System.out::println);
        //names={pork,beef,chicken,french fries,rice,season fruit,pizza,prawns,salmon}
    }

    @Test
    public void mapping() {

        List<Dish> dishList = DishList.getDishList();
        String collect = dishList.stream().collect(Collectors.mapping(Dish::getName, Collectors.joining(",")));
        System.out.println(collect);
        // pork,beef,chicken,french fries,rice,season fruit,pizza,prawns,salmon
    }

    // 卡路里最大的dish
    @Test
    public void maxBy() {
        List<Dish> dishList = DishList.getDishList();
        Optional<Dish> collect = dishList.stream().collect(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)));
        collect.ifPresent(System.out::println);
        // Dish(name=pork, vegetaian=false, calories=800, type=MEAT)
    }


    // 卡路里最小的dish
    @Test
    public void minBy() {
        List<Dish> dishList = DishList.getDishList();
        Optional<Dish> collect = dishList.stream().collect(Collectors.minBy(Comparator.comparingInt(Dish::getCalories)));
        collect.ifPresent(System.out::println);
        // Dish(name=season fruit, vegetaian=true, calories=120, type=OTHER)
    }


    // partitioningBy  分组 依据返回的 true /false （依据是否为肉类分组）
    @Test
    public void partitioningBy() {
        List<Dish> dishList = DishList.getDishList();
        Map<Boolean, List<Dish>> collect = dishList.stream().collect(Collectors.partitioningBy(item -> item.getType().equals(Dish.Type.MEAT)));
        Optional.ofNullable(collect).ifPresent(System.out::println);
/*{
 false= [Dish(name=french fries, vegetaian=true, calories=530, type=OTHER), Dish(name=rice, vegetaian=true, calories=350, type=OTHER),
        Dish(name=season fruit, vegetaian=true, calories=120, type=OTHER), Dish(name=pizza, vegetaian=true, calories=550, type=OTHER),
        Dish(name=prawns, vegetaian=false, calories=300, type=FISH), Dish(name=salmon, vegetaian=false, calories=450, type=FISH)],
 true=  [Dish(name=pork, vegetaian=false, calories=800, type=MEAT), Dish(name=beef, vegetaian=false, calories=700, type=MEAT),
        Dish(name=chicken, vegetaian=false, calories=400, type=MEAT)]
        }

 */
    }


    // 依据是否为肉类分组，然后求卡路里的平均值
    @Test
    public void partitioningBy1() {
        List<Dish> dishList = DishList.getDishList();
        Map<Boolean, Double> collect = dishList.stream().collect(Collectors.partitioningBy(item -> item.getType().equals(Dish.Type.MEAT), Collectors.averagingInt(Dish::getCalories)));
        Optional.ofNullable(collect).ifPresent(System.out::println);
        // {false=383.3333333333333, true=633.3333333333334}

    }


    // 获取卡路里最大的
    @Test
    public void reducing() {
        List<Dish> dishList = DishList.getDishList();
        Optional<Dish> collect = dishList.stream().collect(Collectors.reducing(BinaryOperator.maxBy(Comparator.comparingInt(Dish::getCalories))));
        collect.ifPresent(System.out::println);
        // Dish(name=pork, vegetaian=false, calories=800, type=MEAT)
    }

    // 卡路里的总和  identity 基础值
    @Test
    public void reducing1() {
        List<Dish> dishList = DishList.getDishList();
        Integer collect = dishList.stream().map(Dish::getCalories).collect(Collectors.reducing(10, (d1, d2) -> d1 + d2));
        System.out.println(collect);    // 4210
    }

    // 卡路里的总和  identity 基础值  map操作放到了reducing中
    @Test
    public void reducing2() {
        List<Dish> dishList = DishList.getDishList();
        Integer collect = dishList.stream().collect(Collectors.reducing(10, Dish::getCalories, (d1, d2) -> d1 + d2));
        System.out.println(collect);    // 4210
    }


    // summarizingDouble 统计 卡路里的最大值，最小值，平均值，总数，总计
    @Test
    public void summarizingDouble() {
        List<Dish> dishList = DishList.getDishList();
        DoubleSummaryStatistics collect = dishList.stream().collect(Collectors.summarizingDouble(Dish::getCalories));
        Optional.ofNullable(collect).ifPresent(System.out::println);  //  DoubleSummaryStatistics{count=9, sum=4200.000000, min=120.000000, average=466.666667, max=800.000000}
    }

    // summingDouble  总计
    @Test
    public void summingDouble() {
        List<Dish> dishList = DishList.getDishList();
        Double collect = dishList.stream().collect(Collectors.summingDouble(Dish::getCalories));
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }


    // toCollection 转换为各种集合类型
    @Test
    public void toCollectionArrayList() {
        List<Dish> dishList = DishList.getDishList();
        ArrayList<Dish> collect = dishList.stream().collect(Collectors.toCollection(ArrayList::new));
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }


    @Test
    public void toMap() {
        List<Dish> dishList = DishList.getDishList();
        Map<String, Integer> collect = dishList.stream().collect(Collectors.toMap(Dish::getName, Dish::getCalories));
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }

    // toConcurrentMap
    @Test
    public void toConcurrentMap() {
        List<Dish> dishList = DishList.getDishList();
        Map<String, Integer> collect = dishList.stream().collect(Collectors.toConcurrentMap(Dish::getName, Dish::getCalories));
        Optional.ofNullable(collect).ifPresent(System.out::println);
        System.out.println(collect.getClass()); //class java.util.concurrent.ConcurrentHashMap
    }

    /**
     * Function<? super T, ? extends K> keyMapper,
     * Function<? super T, ? extends U> valueMapper,
     * BinaryOperator<U> mergeFunction  使用某种规则替代 value
     */
    // 每一个dish的类型+1
    @Test
    public void toConcurrentMap1() {
        List<Dish> dishList = DishList.getDishList();
        ConcurrentMap<Dish.Type, Long> collect = dishList.stream().collect(Collectors.toConcurrentMap(Dish::getType, v -> 1L, (d1, d2) -> d1 + d2));
        Optional.ofNullable(collect).ifPresent(System.out::println);
        System.out.println(collect.getClass()); //class java.util.concurrent.ConcurrentHashMap
    }

    // 指定数据结构的：每一个dish的类型+1
    @Test
    public void toConcurrentMap2() {
        List<Dish> dishList = DishList.getDishList();
        ConcurrentMap<Dish.Type, Long> collect = dishList.stream().collect(Collectors.toConcurrentMap(Dish::getType, v -> 1L, (d1, d2) -> d1 + d2, ConcurrentSkipListMap::new));
        Optional.ofNullable(collect).ifPresent(System.out::println);
        System.out.println(collect.getClass()); // class java.util.concurrent.ConcurrentSkipListMap

    }


    // sort
    @Test
    public void sort() {
        List<Dish> dishList = DishList.getDishList();
        //先按照类型，再按照重量排序
        Comparator<Dish> comparator = Comparator.comparing(Dish::getName).thenComparingInt(Dish::getCalories);
        List<Dish> collect = dishList.parallelStream().sorted(comparator).collect(Collectors.toList());
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }

    @Test
    public void reduce() {

    }


}
