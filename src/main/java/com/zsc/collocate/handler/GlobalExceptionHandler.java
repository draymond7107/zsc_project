package com.zsc.collocate.handler;
/**
 * 文件对象
 * 功能说明：<br>
 * 模块名称：<br>
 * 功能描述：<br>
 * 文件名称: <br>
 * 系统名称：<br>
 * 软件著作权：icelove 版权所有<br>
 * 开发人员：icelove <br>
 * 开发时间：2019/2/22 16:38<br>
 * 系统版本：1.0.0<br>
 */
import com.github.pagehelper.util.StringUtil;
import com.zsc.base.exception.SysException;
import com.zsc.base.exception.TokenException;
import com.zsc.base.spring.JsonResult;
import com.zsc.base.utils.JsonUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理
 */
@RestControllerAdvice(basePackages ="com.zsc.tbpractice.controller" )
public class GlobalExceptionHandler {
    private Log logger = LogFactory.getLog(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public JsonResult defaultErrorHandler(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        String url = request.getRequestURI() + (StringUtil.isNotEmpty(request.getQueryString()) ? ("?" + request.getQueryString()) : "");
        JsonResult result = new JsonResult(false);
        result.setRet(500);
        result.setMsg("error");
        if (ex instanceof TokenException) {
            result.setMsg(ex.getMessage());
        } else if (ex instanceof SysException) {
            result.setRet(((SysException) ex).getErrorCode());
            result.setMsg(ex.getMessage());
        } else if (ex instanceof NoHandlerFoundException) {
            NoHandlerFoundException notFindEx = (NoHandlerFoundException)ex;
            result.setRet(404);
            result.setMsg("找不到该接口: "+ JsonUtils.toStringNoEx(notFindEx));
        } else if (ex instanceof org.springframework.web.HttpRequestMethodNotSupportedException) {
            result.setRet(403);
            result.setMsg("不支持该请求方式");
        } else {
            result.setMsg("对不起！服务器趴窝了！" + ex.getClass().getName()+">>>>"+ex.getMessage());
            ex.printStackTrace();
        }
        logger.error("运行异常: "+result.getMsg());
        return result;
    }
}