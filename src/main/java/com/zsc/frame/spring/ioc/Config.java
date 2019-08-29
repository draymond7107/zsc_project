package com.zsc.frame.spring.ioc;


import org.springframework.context.annotation.*;

/**
 * 使用注解的方式配置
 *
 * @author ZhangSuchao
 * @create 2019/8/26
 * @since 1.0.0
 */
//FilterType.ANNOTATION 使用注解策略
//FilterType.ASSIGNABLE_TYPE 按照指定的类型

@Import({Red.class})  //#06
@Configuration      //#01
//@ComponentScan(value = "com.draymond.queue", useDefaultFilters = false, excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {LinkedBlockingQueueDemo.class})})
//@ComponentScans()
@ComponentScan(value = "com.zsc.frame.spring")  //#03
public class Config {

    //  prototype,多实例   singleton ,单实例（默认，ioc默认实例一个对象）
    @Scope("singleton") //#04
    //给容器注册一个bean,将bean放到spring ioc容器管理,类似包扫描
    //类型是方法返回值，id是方法名
    @Bean(value = "persion")     //#02
    @Lazy(value = true)         //#05
    public Persion persion() {
        return new Persion(18, "里斯");
    }


    @Conditional(value = LinuxCondition.class)
    @Bean("linux")
    public Persion linux() {
        return new Persion(56, "linux");
    }

    @Conditional(value = WindowsCondition.class)
    @Bean("window")
    public Persion window() {
        return new Persion(64, "window");
    }
}

