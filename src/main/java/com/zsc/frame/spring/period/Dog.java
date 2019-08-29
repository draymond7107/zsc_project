package com.zsc.frame.spring.period;


import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author ZhangSuchao
 * @create 2019/8/27
 * @since 1.0.0
 */
@Component  //#2.2.3
public class Dog {
    public Dog() {
        System.out.println("Dog constructor....");
    }
    //
    @PostConstruct
    public void init() {
        System.out.println("Dog init.....");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Dog destory.....");
    }

}
