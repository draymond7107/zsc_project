package com.zsc.frame.spring.period;


import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author ZhangSuchao
 * @create 2019/8/27
 * @since 1.0.0
 */
// #2.2.2 再通过包扫描注入到ioc
@Component
public class Cat implements InitializingBean, DisposableBean {


    public Cat() {
        System.out.println("Cat constructor.....");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Cat destory.....");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //对象创建完成，属性赋值完成后调用
        System.out.println("Cat afterPropertiesSet.....");
    }
}
