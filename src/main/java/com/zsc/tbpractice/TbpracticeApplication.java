package com.zsc.tbpractice;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//@EnableTransactionManagement  //开启事务(自己配置事务需要此注解，使用默认的不需要)
@MapperScan({"com.zsc.tbpractice.general.dao", "com.zsc.tbpractice.dao"}) //单个不带{}
@SpringBootApplication
public class TbpracticeApplication extends SpringBootServletInitializer {  //3,修改启动类，并重写初始化方法
    static Log logger = LogFactory.getLog(TbpracticeApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TbpracticeApplication.class, args);
        logger.debug("项目启动完成");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(TbpracticeApplication.class);
    }


}
