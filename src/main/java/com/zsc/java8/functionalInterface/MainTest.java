package com.zsc.java8.functionalInterface;


import org.junit.Test;

/**
 * @author ZhangSuchao
 * @create 2019/9/2
 * @since 1.0.0
 */

public class MainTest {

    //##############  1 无参无返回值函数   ###################
    @Test
    public void noParaAndReturn() {
        // ######### 内部类实现 #########
        NoParaAndReturn innerClass = new NoParaAndReturn() {
            @Override
            public void printParameter() {
                System.out.println("内部类实现：这是一个小米手机");
            }
        };
        innerClass.printParameter();

        //######## lamda类实现 #########
        NoParaAndReturn lambda = () -> {
            System.out.println("lamda类实现：这是一个华为手机");
        };
        lambda.printParameter();
    }

    //##############  2 有参无返回值函数   ###################
    @Test
    public void haveParaAndNoReturn() {
        // ######### 内部类实现 #########
        HaveParaAndNoReturn innerClass = new HaveParaAndNoReturn() {
            @Override
            public void printParameter(String color, String inch) {
                System.out.println("内部类实现:华为p30颜色：" + color + "英寸：" + inch);
            }
        };
        innerClass.printParameter("白色", "5.2");

        //######## lamda类实现 #########
        HaveParaAndNoReturn lambda = (color, inch) -> System.out.println("lamda类实现:华为p30颜色：" + color + "英寸：" + inch);
        lambda.printParameter("白色", "5.2");
    }

    //##############  3 无参有返回值函数   ###################
    @Test
    public void noParaAndHaveReturn() {
        // ######### 内部类实现 #########
        NoParaAndHaveReturn innerClass = new NoParaAndHaveReturn() {
            @Override
            public int printParameter() {
                System.out.println("内部类实现:定义返回值 0");
                return 0;
            }
        };
        int innerClassReturn = innerClass.printParameter();
        System.out.println("内部类实现输出返回值" + innerClassReturn);

        //######## lamda类实现 #########
        NoParaAndHaveReturn lambda = () -> {
            System.out.println("lamda类实现:定义返回值 0");
            return 0;
        };
        int lambdaReturn = lambda.printParameter();
        System.out.println("lamda类实现输出返回值" + lambdaReturn);
    }

    //##############  4 有参数有返回值函数   ###################
    @Test
    public void HaveParaAndHaveReturn() {

        // ######### 内部类实现 #########
        HaveParaAndHaveReturn innerClass = new HaveParaAndHaveReturn() {
            @Override
            public int printParameter(String color, String inch) {
                System.out.println("内部类实现:定义返回值 0");
                return 0;
            }
        };
        int innerClassReturn = innerClass.printParameter("白色", "5.2");
        System.out.println("内部类实现输出返回值" + innerClassReturn);

        //######## lamda类实现 #########

        HaveParaAndHaveReturn lambda = (colar, inch) -> {
            System.out.println("lamda类实现:定义返回值 0");
            return 0;
        };
        int lombdaReturn = lambda.printParameter("白色", "5.2");
        System.out.println("lamda类实现输出返回值" + lombdaReturn);


    }
}
