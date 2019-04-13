package com.zsc.collocate.spring;


import com.zsc.collocate.interceptors.LoginInterceptor;
import com.zsc.collocate.interceptors.TestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author ZhangSuchao
 * @create 2019/4/12
 * @since 1.0.0
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {  //WebMvcConfigurerAdapter

    //拦截器注册
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TestInterceptor()).addPathPatterns("/**");  //  /**下的每一个接口都要有"登录"的校验
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/mes");//mes下的接口都要被拦截
        super.addInterceptors(registry);
    }

    //全局跨域
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")                      // 允许跨域访问的路径
//         .allowedOrigins("http://domain2.com")                    //允许跨域访问的源
//                .allowedMethods("PUT", "DELETE")                  //允许请求方法
//                .allowedHeaders("header1", "header2", "header3")  //允许头部设置
//                .exposedHeaders("header1", "header2")
//                .allowCredentials(false).maxAge(3600);            //是否发送cookie，预检间隔时间
        ;
    }
//如果有安全框架，需要在框架中启用CORS;还有一种基于"过滤器"的跨域设置


}
