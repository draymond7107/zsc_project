package com.zsc.frame.spring.assemble;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author ZhangSuchao
 * @create 2019/8/27
 * @since 1.0.0
 */
@PropertySource("classpath:/application.properties")
@Configuration
public class Config {
    
    @Bean
    public Persion persion() {
        return new Persion();
    }
}
