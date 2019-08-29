package com.zsc.frame.spring.ioc;


import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author ZhangSuchao
 * @create 2019/8/26
 * @since 1.0.0
 */

public class MainTest {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

    @Test
    public void test1() {
        //xml配置,,返回ioc容器
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("beans.xml");
        Persion persion = (Persion) classPathXmlApplicationContext.getBean("persion");
        System.out.println(persion.toString());
    }

    @Test
    public void test2() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        System.out.println("容器创建完成");
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String s :
                beanDefinitionNames) {
            System.out.println(s);
        }
        System.out.println(beanDefinitionNames);
        Persion persion1 = (Persion) context.getBean("persion");
        Persion persion2 = (Persion) context.getBean("persion");
        System.out.println(persion1 == persion2);
        System.out.println(persion1.toString());

    }


    @Test
    public void test3() {
        String[] definitionNames = context.getBeanDefinitionNames();
        for (String str : definitionNames) {
            System.out.println(str);    //config, persion, linux, window
        }
        //ioc运行环境
        ConfigurableEnvironment environment = context.getEnvironment();
        String property = environment.getProperty("os.name");
        System.out.println(property);   //Windows 10

    }

}
