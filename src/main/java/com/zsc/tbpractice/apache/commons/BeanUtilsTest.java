package com.zsc.tbpractice.apache.commons;


import com.zsc.general.entity.User;
import lombok.Data;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.assertj.core.util.Lists;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ZhangSuchao
 * @create 2019/6/19
 * @since 1.0.0
 */

public class BeanUtilsTest {

    public static void main(String[] args) throws Exception {

        User user = new User();
        user.setTrueName("zsc");
        user.setId(1);
        user.setUserName("zsc");
        user.setClassId(1);
        user.setAge(11);
        user.setSex("M");


        Person persion = new Person();
        persion.setName("Akili");
        persion.setSex(1);
        persion.setAge(24);
        persion.setSchool(null);
        persion.setHobby(new String[]{"摄影", "旅行", "家居", "做饭"});
        persion.setPlace(Lists.newArrayList("北京", "深圳", "广州", "北海"));

        cloneBean(user);                //user2user
        copyProperties(user);           //map2user / user2user
        copyProperty(user);             // copyProperty(Object bean, String name, Object value)
        populate(persion);
        setProperty(persion);
        describe(user);
        getArrayProperty(persion);
        getIndexedProperty(persion);
        getMappedProperty(persion);
        getNestedProperty(persion);
        getProperty(persion);
        getSimpleProperty(persion);

    }

    /**
     * 克隆对象
     *
     * @throws Exception
     */
    public static void cloneBean(User user) throws Exception {

        User cloneBean = (User) BeanUtils.cloneBean(user);
        System.out.println(11);
    }

    /**
     * 赋值
     * map2user / user2user
     *
     * @throws Exception
     */
    public static void copyProperties(User user) throws Exception {
        User newUser0 = new User();
        BeanUtils.copyProperties(newUser0, user);

        //Map2User
        User newUser1 = new User();
        Map map1 = new HashMap();
        map1.put("classId", 1);
        map1.put("userName", "zsc");
        map1.put("sex", "M");
        map1.put("age", 22);
        BeanUtils.copyProperties(newUser1, map1);

        //User2Map  error
        Map map2 = new HashMap();
        BeanUtils.copyProperties(map2, user);
        System.out.println(11);
    }

    /**
     * 向user对象赋值
     *
     * @param user
     * @throws Exception
     */
    public static void copyProperty(User user) throws Exception {

        // copyProperty(Object bean, String name, Object value)
        BeanUtils.copyProperty(user, "trueName", "hhh");
        System.out.println(111);

    }

    /**
     * 将Map中的值赋值给person
     *
     * @param person
     * @throws Exception
     */
    public static void populate(Person person) throws Exception {

        //populate(Object bean, Map<String, ? extends Object> properties)
        Map map = new HashMap();
        map.put("name", "hhhhh");
        BeanUtils.populate(person, map);

        System.out.println(11);
    }

    /**
     * 向person对象赋值
     *
     * @param person
     * @throws Exception
     */
    public static void setProperty(Person person) throws Exception {

        BeanUtils.setProperty(person, "name", "hhhhh");
        System.out.println(11);
    }


    /**
     * 将user对象数据赋值到Map
     * user对象有arr[]不适用
     *
     * @param user
     * @throws Exception
     */
    public static void describe(User user) throws Exception {
        Person p = new Person();
        p.setName("Akili");
        p.setSex(1);
        p.setAge(24);
        p.setSchool(null);
        p.setHobby(new String[]{"摄影", "旅行", "家居", "做饭"});
        p.setPlace(Lists.newArrayList("北京", "深圳", "广州", "北海"));


        // copyProperty(Object bean, String name, Object value)
        Map<String, String> describe = BeanUtils.describe(user);


        Map<String, Object> propertyLog = new HashMap<String, Object>();
        Map<String, Object> describe1 = PropertyUtils.describe(user);


        System.out.println(111);

    }

    /**
     * 根据key获取user对象中的属性
     * 返回集合
     *
     * @param person
     * @throws Exception
     */
    public static void getArrayProperty(Person person) throws Exception {
        String[] place = BeanUtils.getArrayProperty(person, "place");
        String[] hobby = BeanUtils.getArrayProperty(person, "hobby");
        System.out.println(11);
    }

    /**
     * 根据key获取user对象中的属性
     * 返回String（集合中的索引位置的value  ）
     *
     * @param person
     * @throws Exception
     */
    public static void getIndexedProperty(Person person) throws Exception {
        String place = BeanUtils.getIndexedProperty(person, "place[0]");
        String hobby = BeanUtils.getIndexedProperty(person, "hobby", 1);
        System.out.println(11);
    }

    /**
     * 未知方法
     *
     * @param person
     * @throws Exception
     */
    public static void getMappedProperty(Person person) throws Exception {
        Map<String, String> map = BeanUtils.describe(person);
        // TODO: 2019/6/20
        String mappedProperty = BeanUtils.getMappedProperty(map, "place", "1");
        System.out.println(11);

    }

    /**
     * 未知方法
     *
     * @param person
     * @throws Exception
     */
    public static void getNestedProperty(Person person) throws Exception {

        String mappedProperty = BeanUtils.getMappedProperty(person, "place");
        System.out.println(111);
    }

    /**
     * 获取user对象的属性，如果属性是arr[]返回第一个
     *
     * @param person
     * @throws Exception
     */
    public static void getProperty(Person person) throws Exception {
        String name = BeanUtils.getProperty(person, "name");
        String place = BeanUtils.getProperty(person, "place");
        System.out.println(place);  //北京

    }

    /**
     * 获取user对象的属性，如果属性是arr[]返回第一个
     * todo 与getProperty的区别？？
     *
     * @param person
     * @throws Exception
     */
    public static void getSimpleProperty(Person person) throws Exception {
        String name = BeanUtils.getSimpleProperty(person, "name");
        String place = BeanUtils.getSimpleProperty(person, "place");
        System.out.println(place);  //北京

    }


}

@Data
class Person {

    private String name;
    private Integer sex;
    private Integer age;
    private String school;
    private String[] hobby;
    private List<String> place;

}
