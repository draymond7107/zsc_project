package com.zsc.frame.spring.period;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author ZhangSuchao
 * @create 2019/8/27
 * @since 1.0.0
 */
@Component
public class Hourse implements BeanPostProcessor {

    public Hourse() {
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessBeforeInitialization......");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization.....");
        return bean;
    }
}
