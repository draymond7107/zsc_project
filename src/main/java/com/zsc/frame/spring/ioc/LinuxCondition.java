package com.zsc.frame.spring.ioc;


import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;

/**
 * 判断系统是否为linux
 *
 * @author ZhangSuchao
 * @create 2019/8/27
 * @since 1.0.0
 */
@Component
public class LinuxCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        Environment environment = conditionContext.getEnvironment();
        String linux = environment.getProperty("os.name");
        if (linux.contains("linux")) return true;
        return false;

    }
}
