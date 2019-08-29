package com.zsc.frame.spring.ioc;


import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;

/**
 * 判断系统是否为window
 *
 * @author ZhangSuchao
 * @create 2019/8/27
 * @since 1.0.0
 */
@Component
public class WindowsCondition implements Condition {

    //conditionContext:上下文环境  annotatedTypeMetadata：注释信息
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        //  1,ioc使用的beanFactory
        ConfigurableListableBeanFactory beanFactory = conditionContext.getBeanFactory();
        //  2,类加载器
        ClassLoader classLoader = conditionContext.getClassLoader();
        //  3,bean定义的注册了类，所有的bean注册都使用此注册类
        BeanDefinitionRegistry registry = conditionContext.getRegistry();

        // TODO: 2019/8/27  添加各种条件筛选
        boolean b = registry.containsBeanDefinition("persion");
        if (!b) return false;

        //  4,当前环境变量
        Environment environment = conditionContext.getEnvironment();
        String osName = environment.getProperty("os.name");
        if (osName.contains("Windows")) return true;
        return false;
    }
}
