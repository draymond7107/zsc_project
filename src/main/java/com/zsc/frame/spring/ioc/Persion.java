package com.zsc.frame.spring.ioc;


import lombok.Data;

/**
 * @author ZhangSuchao
 * @create 2019/8/26
 * @since 1.0.0
 */

@Data
public class Persion {
    private Integer age;
    private String name;

    public Persion(Integer age, String name) {
        System.out.println("创建bean");
        this.age = age;
        this.name = name;
    }

    public Persion() {
    }
}
