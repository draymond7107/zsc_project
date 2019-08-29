package com.zsc.collocate.filter;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import javax.servlet.FilterConfig;
import java.io.IOException;

/**
 * 过滤器业务类
 *
 * @author ZhangSuchao
 * @create 2019/4/12
 * @since 1.0.0
 */

public class AdminLoginFilter implements Filter {
    static Log logger = LogFactory.getLog(AdminLoginFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.debug("logger过滤器的init执行");
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.debug("logger过滤器doFilter开始执行");
        filterChain.doFilter(servletRequest, servletResponse);
        logger.debug("logger接口执行完毕");
    }
    @Override
    public void destroy() {
    }
}
