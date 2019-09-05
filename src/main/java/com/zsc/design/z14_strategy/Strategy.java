package com.zsc.design.z14_strategy;

/**
 * 策略模式
 * 的那个工艺一系列的算法，并将算法封装，使他们可以相互替换，且算法的变化不影响到使用算法的客户（兼容原来的算法），
 * 需要设计一个接口，为一系列实现类提供统一的方法，多个实现类实现该接口，重写其方法。
 * 换策略，只需要new 不同的对象
 * <p>
 * 多线程使用策略模式
 * 多线程的Runnable相当于统一接口
 */
public class Strategy {


}

//提供统一计算接口
interface Icalculator {
    int calculator(String exp);
}

//辅助类（可以没有）
abstract class AbstractCalculator {

    //将 数字拼接的字符串按照opt分割成int类型数组
    public int[] split(String exp, String opt) {
        String array[] = exp.split(opt);
        int[] arrInt = new int[2];
        arrInt[0] = Integer.parseInt(array[0]);
        arrInt[1] = Integer.parseInt(array[1]);
        return arrInt;
    }
}

//实现类(+)
class Plus extends AbstractCalculator implements Icalculator {

    @Override
    public int calculator(String exp) {
        int[] split = super.split(exp, "\\+");
        return split[0] + split[1];
    }
}

//实现类(-)
class Dev extends AbstractCalculator implements Icalculator {

    @Override
    public int calculator(String exp) {
        int[] split = super.split(exp, "\\-");
        return split[0] - split[1];
    }
}

//实现类(*)
class Muti extends AbstractCalculator implements Icalculator {

    @Override
    public int calculator(String exp) {
        int[] split = super.split(exp, "\\*");
        return split[0] * split[1];
    }
}

class StrategyTest {

    public static void main(String[] args) {

        String exp = "2+8";
        Icalculator plus = new Plus();
        int calculator = plus.calculator(exp);
        System.out.println(calculator);

        //lambda 表达式
        Icalculator plusLambda = (x) -> {
            String[] split = x.split("\\+");
            int sum = Integer.parseInt(split[0]) + Integer.parseInt(split[1]);
            return sum;
        };
        int sum = plusLambda.calculator("3+10");
        System.out.println(sum);
    }
}

