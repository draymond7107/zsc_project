package com.zsc.jvm.classLoad;


/**
 * @author ZhangSuchao
 * @create 2019/9/5
 * @since 1.0.0
 */

public class Demo {
    //###################   1：begin 构造器加载  #########################################
    public int tem = 1; //非静态变量，初始化时不会被赋值
    public static int staticTem = 1;  //类构造时默认初始值为0，走到这里，后赋值成1

    static {
        staticTem = 2;
        System.out.println("static  " + staticTem);
    }
    //###################   1：end 构造器加载  #########################################


    public static void main(String[] args) {
        //###################   1：test 构造器加载  #########################################
        staticTem = 3;
        System.out.println("main  " + staticTem);
        System.out.println(new Demo().tem);

//###################   2：test 类加载器加载  #########################################

        ClassLoader classLoader = Demo.class.getClassLoader();

        while (classLoader != null) {
            System.out.println("classLoader==" + classLoader);
            classLoader = classLoader.getParent();
        }
        //classLoader==sun.misc.Launcher$AppClassLoader@18b4aac2
        //classLoader==sun.misc.Launcher$ExtClassLoader@5197848c

        System.out.println("classLoader==" + classLoader);    //null 为什么不是Bootstrap? Bootstrap是C++写的，null代表就是Bootstrap
    }

    /*
      classLoad 加载
      if (parent != null) {
            c = parent.loadClass(name, false);
         } else {
            c = findBootstrapClassOrNull(name);   为 null时从Bootstrap加载
         }
     */
}
