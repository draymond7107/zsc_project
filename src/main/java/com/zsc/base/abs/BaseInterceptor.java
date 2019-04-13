package com.zsc.base.abs;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public abstract class BaseInterceptor implements org.springframework.web.servlet.HandlerInterceptor{
	protected String encoding = "UTF=8";
	protected Log logger = LogFactory.getLog(getClass());
	/**
	 * 初始化一些编码格式
	 * @param request
	 * @param response
	 * @param object
	 * @throws Exception
	 */
	protected void init(HttpServletRequest request,HttpServletResponse response,Object object) throws Exception{
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("user_time",System.currentTimeMillis());
		response.setContentType("text/html; charset=" + encoding);
		response.setHeader("Cache-Control","no-cache");
		response.setHeader("Pragma","no-cache");
		response.setHeader("expires","0");
		//String servletPath = request.getServletPath();
		//String method = request.getMethod();
		//String header = request.getHeader("User-Agent");
		//logger.info(servletPath+",header="+header+">>>"+method);
		//response.setContentType("application/json;charset=UTF-8");
		//response.setCharacterEncoding(encoding);
		//System.out.println(response.getContentType());
	}
	
	protected void sendJsonError(HttpServletRequest request,HttpServletResponse response,int ret,String msg) throws Exception{
		response.setHeader("Cache-Control","no-cache");
		response.setHeader("Pragma","no-cache");
		response.setHeader("expires","0");
		response.setContentType("text/json; charset=UTF-8");
		response.getWriter().write("{\"msg\":\""+msg+"\",\"ret\":"+ret+"}");
	}
	
	protected void sendPageError(HttpServletRequest request,HttpServletResponse response,int ret,String msg) throws Exception{
		response.setHeader("Cache-Control","no-cache");
		response.setHeader("Pragma","no-cache");
		response.setHeader("expires","0");
		response.setContentType("text/json; charset=UTF-8");
	}
}
