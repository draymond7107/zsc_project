package com.zsc.frame.spring.assemble;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author ZhangSuchao
 * @create 2019/8/27
 * @since 1.0.0
 */

@Data
public class Persion {
    @Value("张三")
    private String trueName;
    @Value("#{12-1}")
    private Integer age;
    @Value("${persion.name}")
    private String nickName;

    public Persion() {
    }

}
