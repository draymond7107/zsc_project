package com.zsc.frame.spring.period;


import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author ZhangSuchao
 * @create 2019/8/27
 * @since 1.0.0
 */

public class MainTest {

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

    @Test    // #2.2.1
    public void test1() {
        System.out.println("spring 容器创建完成");
        Car car = (Car) context.getBean("car");

        //关闭容器：先销毁ioc容器里面的bean再关闭
        context.close();
    }
}
