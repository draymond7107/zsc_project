package com.zsc.collocate.interceptors;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器-业务测试拦截
 *
 * @author ZhangSuchao
 * @create 2019/4/12
 * @since 1.0.0
 */

public class TestInterceptor implements HandlerInterceptor {

    static Log logger = LogFactory.getLog(TestInterceptor.class);
    long start = System.currentTimeMillis();

    /*
       preHandle是请求执行前执行的，
    */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        logger.error("LoginCostInterceptor的preHandle拦截器执行");
        start = System.currentTimeMillis();
        return true;
    }

    /*
    postHandler是请求结束执行的，但只有preHandle方法返回true的时候才会执行，
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        logger.error("LoginCostInterceptor的postHandle拦截器执行"+"\"Interceptor cost=\" + (System.currentTimeMillis() - start)");

    }

    /*
     afterCompletion是视图渲染完成后才执行，同样需要preHandle返回true，该方法通常用于清理资源等工作。除了实现上面的接口外，
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {


    }
}
