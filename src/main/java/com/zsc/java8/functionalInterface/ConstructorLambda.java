package com.zsc.java8.functionalInterface;




//构造器的引用
@FunctionalInterface
public interface ConstructorLambda {

    String test(char[] str);
}

class TestConstructorLambda {
    public static void main(String[] args) {
        String s = new String();
        //引用构造器：根据函数式接口的方法名来推断使用哪个构造器
        ConstructorLambda constructorLambda = String::new;
        constructorLambda.test(new  char[]{'1','2','啊'});
    }
}
