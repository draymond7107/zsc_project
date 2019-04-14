package com.zsc.collocate.interceptors;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器-登录业务拦截
 *在com.zsc.collocate.spring.WebMvcConfig注册Bean
 * @author ZhangSuchao
 * @create 2019/4/12
 * @since 1.0.0
 */

public class LoginInterceptor implements HandlerInterceptor {
    static Log logger = LogFactory.getLog(LoginInterceptor.class);

    //登录之前的校验
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.debug("登录之前的校验");

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
