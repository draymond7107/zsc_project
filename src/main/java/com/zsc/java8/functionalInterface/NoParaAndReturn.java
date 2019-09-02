package com.zsc.java8.functionalInterface;


/***
 * 函数式编程
 *
 * @author ZhangSuchao
 * @create 2019/9/2
 * @since 1.0.0
 */

/**
 * 无参无返回值函数
 */
@FunctionalInterface
public interface NoParaAndReturn {
    //打印参数
    void printParameter();
}


//有参无返回值函数
@FunctionalInterface
interface HaveParaAndNoReturn {
    //打印参数
    void printParameter(String color, String inch);
}

//无参有返回值函数
interface NoParaAndHaveReturn {
    int printParameter();
}

//有参数有返回值
interface HaveParaAndHaveReturn {
    int printParameter(String color, String inch);
}


