package com.zsc.frame.spring.assemble;


import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author ZhangSuchao
 * @create 2019/8/28
 * @since 1.0.0
 */

public class MainTest {

    @Test
    public void test1() {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Persion persion = (Persion) context.getBean("persion");
        System.out.println(persion);

    }
}
