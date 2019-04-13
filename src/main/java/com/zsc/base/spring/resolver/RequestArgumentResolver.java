package com.zsc.base.spring.resolver;
import com.zsc.base.utils.RequestUtils;
import com.zsc.base.vo.ClientInfo;
import com.zsc.base.spring.annotation.RequestClient;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
/**
 * request请求参数获取器,如果请求需要很多参数则用这个
 * 功能说明：<br>
 * 模块名称：<br>
 * 功能描述：LoginedArgumentResolver<br>
 * 文件名称: LoginedArgumentResolver.java<br>
 * 系统名称：ICELOVE<br>
 * 软件著作权：ICELOVE 版权所有<br>
 * 开发人员：lujun <br>
 * 开发时间：2016-8-29 下午9:56:54<br>
 * 系统版本：1.0.0<br>
 */
public class RequestArgumentResolver implements HandlerMethodArgumentResolver{
	@Override
	public boolean supportsParameter(MethodParameter parameter){
		if(parameter.getParameterAnnotation(RequestClient.class) != null && parameter.getParameterType() == ClientInfo.class){
		    return true;
        }
		return false;
    }

	@Override
	public Object resolveArgument(MethodParameter parameter,ModelAndViewContainer mavContainer,NativeWebRequest webRequest,WebDataBinderFactory binderFactory) throws Exception{
        //System.out.println("resolveArgument");
	    HttpServletRequest request = (HttpServletRequest)webRequest.getNativeRequest();
        ClientInfo clientInfo = new ClientInfo();
        clientInfo.setProduct(RequestUtils.getStrPar(request,"product"));
        clientInfo.setClientType(RequestUtils.getStrPar(request,"clientType"));
        clientInfo.setVer(RequestUtils.getStrPar(request,"ver"));
        //clientInfo.setChannel(RequestUtils.getStrPar(request,"channel"));
        clientInfo.setApiVer(RequestUtils.getStrPar(request,"apiVer"));
        clientInfo.setApiLevel(RequestUtils.getIntPar(request,"apiLevel"));
        clientInfo.setDeviceId(RequestUtils.getStrPar(request,"deviceId"));
        clientInfo.setRemoteIp(RequestUtils.getRemoteIp(request));
        clientInfo.setToken(RequestUtils.getStrPar(request,"token"));
        clientInfo.setSign(RequestUtils.getStrPar(request,"sign"));
        //clientInfo.init();
        //如果有登陆信息还可以吧登陆信息放进去
        return clientInfo;
	}
}
