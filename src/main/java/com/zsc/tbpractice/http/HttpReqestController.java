package com.zsc.tbpractice.http;


import com.zsc.base.abs.BaseController;
import com.zsc.base.spring.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.Map;

/**
 * @author ZhangSuchao
 * @create 2019/4/24
 * @since 1.0.0
 */
@RestController
@RequestMapping("/request")
public class HttpReqestController extends BaseController {

    @RequestMapping("/httpTest")
    public JsonResult httpTest(HttpServletRequest request, HttpServletResponse response) {
        //获得客户机信息
        StringBuffer requestURL = request.getRequestURL();  //方法返回客户端发出请求时的完整URL
        logger.debug("requestURL=" + requestURL.toString());
        String requestURI = request.getRequestURI();        //返回请求行中的资源名部分。
        logger.debug("requestURI=" + requestURI);
        String queryString = request.getQueryString();      //返回请求行中的参数部分
        logger.debug("queryString=" + queryString);
        String pathInfo = request.getPathInfo();            //返回请求URL中的额外路径信息。额外路径信息是请求URL中的位于Servlet的路径之后和查询参数之前的内容，它以“/”开头。
        logger.debug("pathInfo=" + pathInfo);
        String remoteAddr = request.getRemoteAddr();        //返回发出请求的客户机的IP地址。
        logger.debug("remoteAddr=" + remoteAddr);
        String remoteHost = request.getRemoteHost();        //发出请求的客户机的完整主机名。
        logger.debug("remoteHost=" + remoteHost);
        int remotePort = request.getRemotePort();           //返回客户机所使用的网络端口号。
        logger.debug("remotePort=" + remotePort);
        String localAddr = request.getLocalAddr();          //返回WEB服务器的IP地址。
        logger.debug("localAddr=" + localAddr);
        String localName = request.getLocalName();          //返回WEB服务器的主机名。
        logger.debug("localName=" + localName);


        //获得客户机请求头
        String header = request.getHeader("Accept");            //获取Accept请求头对应的值
        logger.debug("header=" + header);

        Enumeration<String> headerNames = request.getHeaderNames();//获取所有的请求头
        while (headerNames.hasMoreElements()) {
            String headName = (String) headerNames.nextElement();
            String headValue = request.getHeader(headName);         //根据请求头的名字获取对应的请求头的值
            logger.debug("headName=" + headValue);
        }

        Enumeration<String> e = request.getHeaders("Accept");
        while (e.hasMoreElements()) {
            String string = (String) e.nextElement();
            System.out.println(string);
        }


        //获得客户机请求参数(客户端提交的数据)
        String parameter = request.getParameter("userName");    //

        Enumeration<String> parameterNames = request.getParameterNames();//获取所有携带的参数
        String[] userNames = request.getParameterValues("userName");//获取选中的兴趣，因为可以选中多个值，所以获取到的值是一个字符串数组，因此需要使用getParameterValues方法来获取
        Map<String, String[]> parameterMap = request.getParameterMap();
        String hiddenField = request.getParameter("hiddenField");//获取隐藏域的内容
        System.out.println("");

        return null;
    }
}
