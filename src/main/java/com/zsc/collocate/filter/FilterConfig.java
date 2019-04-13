package com.zsc.collocate.filter;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 过滤器注册类
 *
 * @author ZhangSuchao
 * @create 2019/4/12
 * @since 1.0.0
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean registFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new AdminLoginFilter());
        registration.addUrlPatterns("/*");          //url的匹配模式
        registration.setName("LogCostFilter");      //过滤器名称
        registration.setOrder(1);                   //执行顺序
        return registration;
    }
}
