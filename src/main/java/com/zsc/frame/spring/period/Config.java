package com.zsc.frame.spring.period;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author ZhangSuchao
 * @create 2019/8/27
 * @since 1.0.0
 */
@ComponentScan("com.zsc.frame.spring.period")
@Configuration
public class Config {
    // #2.2.1
    @Bean(initMethod = "init",destroyMethod = "destroy")
    public Car car() {
        return new Car();
    }
}
