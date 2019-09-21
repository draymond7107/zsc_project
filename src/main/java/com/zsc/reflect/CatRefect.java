package com.zsc.reflect;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * java类的加载
 * ava类的加载是指将.class文件（可能在磁盘，也可能在网络）加载到内存中，并为之生成java.lang.class对象的实例
 * java中类以其全限定包名作为唯一标识，jvm中则以全限定包名和类加载器作为唯一标识。
 * jvm中的类加载器有三个层次：根类加载器、扩展类加载器、系统类加载器。分别加载核心类，扩展jar包中的类以及classpath等指定的类
 * <p>
 * 对象具有两种类型：编译时类型和运行时类型。
 */
public class CatRefect {
    /**
     * 获取Class的字节码:java.lang.class对象的三种方法
     */
    @Test
    public void getClassOfObject() throws ClassNotFoundException {
        Class<?> catClass_class = Cat.class;
        Class<?> catClass_ForName = Class.forName("com.zsc.reflect.Cat");
        Class<?> catClass_getClass = new Cat().getClass();
    }

    @Test
    public void getClassName() throws Exception {
        Class<?> clazz = Class.forName("com.zsc.reflect.Cat");
        String name = clazz.getName();
        System.out.println("name==" + name);    //name==com.zsc.reflect.Cat
    }

    /**
     * 获取public构造器
     */
    @Test
    public void getPublicContrucate() throws Exception {
        Class<?> clazz = Class.forName("com.zsc.reflect.Cat");

        Cat catByBewInstance = (Cat) clazz.newInstance();
        catByBewInstance.eat("mouse"); //今晚cat吃了：mouse

        Constructor<?> declaredConstructor = clazz.getConstructor();
        Cat cat = (Cat) declaredConstructor.newInstance();
        cat.eat("rat"); //今晚cat吃了：rat
    }

    /**
     * 获取private构造器
     */
    @Test
    public void getPrivateContrucate() throws Exception {
        Class<?> clazz = Class.forName("com.zsc.reflect.Cat");
        Constructor<?> declaredConstructor = clazz.getDeclaredConstructor(String.class, double.class);
        declaredConstructor.setAccessible(true);    //访问“私有属性、私有构造器、私有方法”必须设置此值
        Cat cat = (Cat) declaredConstructor.newInstance("wright", 10.01);
        System.out.println(cat);      //Cat{color='wright', weight=10.01}
        cat.eat("rat");         //今晚cat吃了：rat
    }

    /**
     * 获取全部的public构造器
     */
    @Test
    public void getAllPublicContrucate() throws Exception {
        Class<?> clazz = Class.forName("com.zsc.reflect.Cat");
        Constructor<?>[] constructors = clazz.getConstructors();

        for (Constructor<?> constructor : constructors) {
            String name = constructor.getName();
            System.out.println("publicConstructorName==" + name);   // publicConstructorName==com.zsc.reflect.Cat  共1个
        }
    }

    /**
     * 获取全部的all构造器
     */
    @Test
    public void getAllContrucate() throws Exception {
        Class<?> clazz = Class.forName("com.zsc.reflect.Cat");
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();

        for (Constructor<?> constructor : constructors) {
            String name = constructor.getName();
            System.out.println("publicConstructorName==" + name);
            // publicConstructorName==com.zsc.reflect.Cat  共2个
            // publicConstructorName==com.zsc.reflect.Cat
        }
    }

    /**
     * 获取指定的public属性
     */
    @Test
    public void getOnePublicField() throws Exception {
        Class<?> clazz = Class.forName("com.zsc.reflect.Cat");
        Field field = clazz.getDeclaredField("weight");
        String name = field.getName();
        System.out.println(name);   //weight
    }

    /**
     * 获取指定的public属性
     */
    @Test
    public void getAllPublicField() throws Exception {
        Class<?> clazz = Class.forName("com.zsc.reflect.Cat");
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            String name = field.getName();
            System.out.println(name);   //weight
        }
    }

    /**
     * 获取所有的属性
     */
    @Test
    public void getAllField() throws Exception {
        Class<?> clazz = Class.forName("com.zsc.reflect.Cat");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String name = field.getName();
            System.out.print(name + " ");   //color weight
        }
    }

    /**
     * 获取指定的属性
     */
    @Test
    public void getOneField() throws Exception {
        Class<?> clazz = Class.forName("com.zsc.reflect.Cat");
        Field field = clazz.getDeclaredField("color");
        //    field.setAccessible(true);  //有没有 都能获取到，建议加上
        String name = field.getName();
        System.out.print(name + " ");   //color
    }

    /**
     * 获取public方法
     */
    @Test
    public void getPublicMethod() throws Exception {
        Class<?> clazz = Class.forName("com.zsc.reflect.Cat");
        Object o = clazz.newInstance();
        Method eat = clazz.getMethod("eat", String.class);
        eat.invoke(o, "rat");    //今晚cat吃了：rat
    }

    /**
     * 获取private方法
     */
    @Test
    public void getMethod() throws Exception {
        Class<?> clazz = Class.forName("com.zsc.reflect.Cat");
        Object o = clazz.newInstance();
        Method eat = clazz.getDeclaredMethod("sleep", Integer.class, String.class);
        eat.setAccessible(true);
        eat.invoke(o, 1,"red");    // 1个颜色是red的猫猫在睡觉
    }


}
