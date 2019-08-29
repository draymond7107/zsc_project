package com.zsc.apache.commons;


import com.zsc.tbpractice.general.entity.User;
import org.apache.commons.beanutils.BeanPropertyValueChangeClosure;
import org.apache.commons.beanutils.BeanPropertyValueEqualsPredicate;
import org.apache.commons.beanutils.BeanToPropertyValueTransformer;
import org.apache.commons.collections.CollectionUtils;

import java.util.*;

/**
 * @author ZhangSuchao
 * @create 2019/6/20
 * @since 1.0.0
 */

public class CollectionsTest {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        //  list1.add(4);

        Set<Integer> integerSet = new HashSet<>();
        integerSet.add(1);
        integerSet.add(2);
        integerSet.add(3);
        integerSet.add(4);

        Map map = new HashMap();
        map.put("1", 1);
        map.put("2", "2");
        map.put("3", 3);
        map.put("4", 4);

        //  isEmpty(list);
        //   isEqualCollection(list, integerSet);
        //   union(list,list1);
        //  intersection(list,integerSet);
        // disjunction(list, integerSet);
        //  subtract(list, integerSet);
        //  forAllDo();
        //transformer();
        filter();

    }

    public static void isEmpty(List<Integer> list) {
        boolean empty = CollectionUtils.isEmpty(list);
        boolean notEmpty = CollectionUtils.isNotEmpty(list);
        System.out.println(empty);
    }

    /**
     * 两个集合中的值是否相同
     * 不论集合种类
     *
     * @param list1
     * @param list2
     */
    public static void isEqualCollection(List<Integer> list1, Set<Integer> list2) {
        boolean equalCollection = CollectionUtils.isEqualCollection(list1, list2);
        System.out.println(equalCollection);
    }

    /**
     * 并集
     * 两个全部的值
     *
     * @param list1
     * @param list2
     */
    public static void union(List list1, List list2) {
        list1.add(6);
        Collection union = CollectionUtils.union(list1, list2);
        System.out.println(union);
    }

    /**
     * 交集
     * 两者相同的值
     *
     * @param list1
     * @param integerSet
     */
    public static void intersection(List list1, Set<Integer> integerSet) {
        list1.add(6);
        integerSet.remove(2);
        Collection intersection = CollectionUtils.intersection(list1, integerSet);
        System.out.println(111);
    }

    /**
     * 补集
     * 两者任意一方没有的值
     *
     * @param list1
     * @param integerSet
     */
    public static void disjunction(List list1, Set<Integer> integerSet) {
        list1.add(6);
        integerSet.remove(2);
        integerSet.add(7);
        Collection disjunction = CollectionUtils.disjunction(integerSet, list1);
        System.out.println(111);
    }

    /**
     * 差值
     * integerSet没有的值
     *
     * @param list1
     * @param integerSet
     */
    public static void subtract(List list1, Set<Integer> integerSet) {
        list1.add(6);
        integerSet.remove(2);
        integerSet.add(7);
        Collection subtract = CollectionUtils.subtract(list1, integerSet);
        System.out.println(subtract);

    }

    /**
     * 批量修改某一个属性
     * 适合用于清除列表中密码
     */
    public static void forAllDo() {
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setSex("M");
        user.setAge(11);
        user.setClassId(1);
        user.setUserName("zsc");
        user.setId(1);
        users.add(user);

        User user1 = new User();
        user1.setSex("M");
        user1.setAge(12);
        user1.setClassId(2);
        user1.setUserName("zsc1");
        user1.setId(2);
        users.add(user1);

        //批量修改集合
        BeanPropertyValueChangeClosure closure = new BeanPropertyValueChangeClosure("userName", "");

        CollectionUtils.forAllDo(users, closure);

        System.out.println(111);

    }

    /**
     * 将list中对象的某一个属性，放到一个集合中
     */
    public static void transformer() {
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setSex("M");
        user.setAge(11);
        user.setClassId(1);
        user.setUserName("zsc");
        user.setId(1);
        users.add(user);

        User user1 = new User();
        user1.setSex("M");
        user1.setAge(12);
        user1.setClassId(2);
        user1.setUserName("zsc1");
        user1.setId(2);
        users.add(user1);

        //将集合中所有你user的id传输到另外一个集合上
        BeanToPropertyValueTransformer transformer = new BeanToPropertyValueTransformer("id");
        Collection<?> idList = CollectionUtils.collect(users, transformer);
        List<Integer> list = (List<Integer>) idList;
        System.out.println(111);
    }

    /**
     * // TODO: 2019/7/23 什么作用？怎么过滤的？
     * 过滤集合
     */
    public static void filter() {

        List<User> users = new ArrayList<>();
        User user = new User();
        //  user.setSex("M");
        user.setAge(11);
        user.setClassId(1);
        user.setUserName("zsc");
        user.setId(1);
        users.add(user);

        User user1 = new User();
        user1.setSex("M");
        user1.setAge(12);
        user1.setClassId(2);
        user1.setUserName("zsc1");
        user1.setId(2);
        users.add(user1);


        BeanPropertyValueEqualsPredicate predicate =
                new BeanPropertyValueEqualsPredicate("sex", Boolean.TRUE);

        //过滤集合
        CollectionUtils.filter(users, predicate);
        System.out.println(11);

    }

}
