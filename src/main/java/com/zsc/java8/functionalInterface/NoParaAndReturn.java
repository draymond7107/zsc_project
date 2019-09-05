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

//#################################  高级使用  ####################################################
@FunctionalInterface
interface LambdaReturn {
    int test(int i);
}

class MainTest1 {

    void re(LambdaReturn lambdaReturn) {
        System.out.println("lambda表达式的值"+lambdaReturn.test(1) );
    }

    public static void main(String[] args) {

        //###第一种 使用普通的lambda表达式
        MainTest1 mainTest1 = new MainTest1();
        LambdaReturn lambdaReturn = (i) -> {
            return i;
        };
        mainTest1.re(lambdaReturn);
        //###第二种 使用普通的lambda表达式（简化）
        mainTest1.re((i) -> { return i;   });

        //###第三种 使用普通的lambda表达式（再简化） 只有1行代码，可以省略 {return}
        mainTest1.re((i)->1);

    }

}





