package com.zsc.base.utils;
import com.alibaba.fastjson.JSONObject;
import com.zsc.base.Config;
import com.zsc.base.spring.JsonResult;
import com.zsc.base.vo.UserAgentInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.MediaType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用于Request的工具类
 * @author icelove
 * 
 */
public class RequestUtils {
	public final static String dateKey = "yyyy-MM-dd";
	public final static String timeKey = "yyyy-MM-dd HH:mm:ss";
	final static Log logger = LogFactory.getLog(RequestUtils.class);

	/***************************************************************************
	 * 编码识别
	 * @param req
	 * @return
	 */
	public static boolean isMultipart(HttpServletRequest req) {
		return ((req.getContentType() != null) && (req.getContentType().toLowerCase().startsWith("multipart")));
	}
    /**
     * 判断是否为搜索引擎
     * @param req
     * @return
     */
    public static boolean isRobot(HttpServletRequest req){
        String ua = req.getHeader("user-agent");
        if(StringUtils.isBlank(ua)) return false;
        return (ua != null
                && (ua.indexOf("Baiduspider") != -1 || ua.indexOf("Googlebot") != -1
                        || ua.indexOf("sogou") != -1
                        || ua.indexOf("sina") != -1
                        || ua.indexOf("iaskspider") != -1
                        || ua.indexOf("ia_archiver") != -1
                        || ua.indexOf("Sosospider") != -1
                        || ua.indexOf("YoudaoBot") != -1
                        || ua.indexOf("yahoo") != -1
                        || ua.indexOf("yodao") != -1
                        || ua.indexOf("MSNBot") != -1
                        || ua.indexOf("spider") != -1
                        || ua.indexOf("Twiceler") != -1
                        || ua.indexOf("Sosoimagespider") != -1
                        || ua.indexOf("naver.com/robots") != -1
                        || ua.indexOf("Nutch") != -1 
                        || ua.indexOf("spider") != -1));   
    }
	/**
	 * 获取COOKIE
	 * @param name
	 */
	public static Cookie getCookie(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null)
			return null;
		for (int i = 0; i < cookies.length; i++) {
			if (name.equals(cookies[i].getName())) {
				return cookies[i];
			}
		}
		return null;
	}

	/**
	 * 设置COOKIE
	 * @param name
	 * @param value
	 * @param maxAge
	 */
	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String name, String value, int maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(maxAge);
		String serverName = request.getServerName();
		String domain = getDomainOfServerName(serverName);
		if (domain != null && domain.indexOf('.') != -1) {
			cookie.setDomain('.' + domain);
		}
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	/**
	 * 获取用户访问URL中的根域名 例如: www.dlog.cn -> dlog.cn
	 * @param host
	 * @return
	 */
	public static String getDomainOfServerName(String host) {
		if (StringUtils.isIPAddr(host))
			return null;
		String[] names = StringUtils.split(host, ".");
		int len = names.length;
		if (len >= 2)
			return names[len - 2] + '.' + names[len - 1];
		return host;
	}

	/**
	 * 从URL地址中解析出URL前缀，例如 http://wap.mo168.com:8081/index.jsp ->
	 * http://wap.mo168.com:8081
	 * @param req
	 * @return
	 */
	public static String getPrefixUrl(HttpServletRequest req) {
		StringBuffer url = new StringBuffer(req.getScheme());
		url.append("://");
		url.append(req.getServerName());
		int port = req.getServerPort();
		if (port != 80) {
			url.append(":");
			url.append(port);
		}
		return url.toString();
	}
	/**
	 * 获取访问的URL全路径
	 * @param request
	 * @return
	 */
	public static String getRequestBody(HttpServletRequest request){
		return getRequestBody(request, Config.ENC_UTF);
	}
	public static String getRequestBody(HttpServletRequest request,String charset){
		String body = "";
		try{
			ServletInputStream inputStream = request.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream,charset));
			while(true){
				String info = br.readLine();
				if(info == null){
					break;
				}
				if(body == null || "".equals(body)){
					body = info;
				}else{
					body += info;
				}
			}
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
			return null;
		}catch(IOException e){
			e.printStackTrace();
			return null;
		}
		return body;
	}
	/**
	 * 获取访问的URL全路径
	 * @param request
	 * @return
	 */
	public static String getRequestURL(HttpServletRequest request) {
		StringBuffer url = new StringBuffer(request.getRequestURI());
		String param = request.getQueryString();
		if (param != null) {
			url.append('?');
			url.append(param);
		}
		String path = url.toString();
		return path.substring(request.getContextPath().length());
	}

	/**
	 * 获取header信息，名字大小写无关
	 * @param req
	 * @param name
	 * @return
	 */
	public static String getHeader(HttpServletRequest req, String name) {
		String value = req.getHeader(name);
		if (value != null)
			return value;
		Enumeration<?> names = req.getHeaderNames();
		while (names.hasMoreElements()) {
			String n = (String) names.nextElement();
			if (n.equalsIgnoreCase(name)) {
				return req.getHeader(n);
			}
		}
		return null;
	}

	/**
	 * 打印所有头信息
	 * @param req
	 * @param out
	 */
	public static void dumpHeaders(HttpServletRequest req, PrintStream out) {
		Enumeration<?> hds = req.getHeaderNames();
		out.println("=============== HEADERS ===============");
		while (hds.hasMoreElements()) {
			String name = (String) hds.nextElement();
			out.println(name + "=" + req.getHeader(name));
		}
	}

	/**
	 * 判断手机是否支持某种类型的格式
	 * @param req
	 * @param contentType
	 * @return
	 */
	public static boolean support(HttpServletRequest req, String contentType) {
		String accept = getHeader(req, "accept");
		if (accept != null) {
			accept = accept.toLowerCase();
			return accept.indexOf(contentType.toLowerCase()) != -1;
		}
		return false;
	}

	/**
	 * 判断浏览器是否与Mozilla兼容
	 * @param req
	 * @return
	 */
	public static boolean isMozillaCompatible(HttpServletRequest req) {
		String user_agent = req.getHeader("user-agent");
		return user_agent == null || user_agent.indexOf("Mozilla") != -1;
	}

	public static String getBaseURL(HttpServletRequest req) {
		String url = req.getRequestURL().toString();
		String uri = req.getRequestURI();
		String context = req.getContextPath();
		int idx = url.indexOf(uri);
		return (idx == -1) ? url : url.substring(0, idx) + context;
	}

	/**
	 * 获取提交的参数，默认是""
	 * @param request
	 * @param param
	 * @return
	 */
	public static String getStrPar(HttpServletRequest request, String param) {
		return getStrPar(request, param, "");
	}
	public static String getStrPar(HttpServletRequest request, String param, String defaultValue) {
		String value = request.getParameter(param);
		return (StringUtils.isEmpty(value)) ? defaultValue : value.trim();
	}

	/**
	 * 获取Ajax提交的参数，默认是""
	 * @param request
	 * @param param
	 * @return
	 */
	public static String getAjaxStrPar(HttpServletRequest request, String param) {
		return getAjaxStrPar(request, param, "");
	}

	public static String getAjaxStrPar(HttpServletRequest request, String param, String defaultValue) {
		String value = request.getParameter(param);
		try {
			value = (StringUtils.isEmpty(value)) ? defaultValue : java.net.URLDecoder.decode(value,"utf-8").trim();
		} catch (UnsupportedEncodingException e) {
			return defaultValue;
		}
		return value;
	}

	/**
	 * 获取Str的数组
	 * @param request
	 * @param par
	 * @return
	 */
	public static String[] getStrParArray(HttpServletRequest request, String[] par) {
		String[] str = new String[par.length];
		for (int i = 0; i < str.length; i++) {
			str[i] = getStrPar(request, par[i]);
		}
		return str;
	}

	/**
	 * 得到checkBox值 boolean型 checkBox 没选：null，选中：value
	 * @param request
	 * @param param
	 * @return
	 */
	public static boolean getCheckBoxPar(HttpServletRequest request, String param) {
		String str = request.getParameter(param);
		if (str != null && str.equals("1")) {
			return true;
		} else
			return false;
	}
	/**
	 * 得到checkBox值
	 * @param request
	 * @param parName
	 * @return
	 */
	public static String getCheckBoxVal(HttpServletRequest request, String parName) {
		String[] str = request.getParameterValues(parName);
		if(str!=null && str.length>0){
			return StringUtils.join(str,",");
		}
		return "";
	}

	/**
	 * 获取整型 初始值为 0
	 * @param request
	 * @param param
	 * @return
	 */
	public static int getIntPar(HttpServletRequest request, String param) {
		return getIntPar(request, param, 0);
	}
	public static int getIntPar(HttpServletRequest request, String param, int defValue) {
		int value = defValue;
		try {
			value = Integer.parseInt(request.getParameter(param));
		} catch (Exception e) {
			return defValue;
		}
		return value;
	}

	/**
	 * 获取浮点型 初始值为 0.00
	 * @param request
	 * @param param
	 * @return
	 */
	public static double getDoublePar(HttpServletRequest request, String param) {
		return getDoublePar(request, param, 0.00);
	}
	public static double getDoublePar(HttpServletRequest request, String param, double defValue) {
		double value = defValue;
		try {
			value = Double.parseDouble(request.getParameter(param));
		} catch (Exception e) {
			return defValue;
		}
		return value;
	}

	/**
	 * 获取时间类型
	 * @param request
	 * @param param
	 * @param aMask
	 * @return
	 * @throws ParseException
	 */
	public static Date getDatePar(HttpServletRequest request, String param, String aMask) throws ParseException {
		String strDate = getStrPar(request, param);
		return DateUtils.parse(aMask, strDate);
	}
	public static int[] getIntPars(HttpServletRequest req, String param) {
		String[] str = req.getParameterValues(param);
		if (str == null)
			return null;
		int[] rInt = new int[str.length];
		try {
			for (int i = 0; i < str.length; i++) {
				rInt[i] = Integer.parseInt(str[i]);
			}
		} catch (Exception e) {
			return null;
		}
		return rInt;
	}

	/**
	 * 绑定parameter到一个HashMap
	 * @param request
	 * @return
	 */
	public static Map<String,String> param2Map(HttpServletRequest request) {
		Map<String, String> hashMap = new HashMap<String, String>();
		Map<?,?> map = request.getParameterMap();
		Iterator<?> it = map.keySet().iterator();
		while (it.hasNext()) {
			String parName = (String) it.next();
			String[] str = (String[]) map.get(parName);
			if (str.length == 1) {
				hashMap.put(StringUtils.checkStr(parName), StringUtils.formatNotHtml(str[0]));
			} else {
				String temp = "";
				for (int i = 0; i < str.length; i++) {
					temp += (str[i] + "").trim() + "|";
				}
				temp = temp.substring(0, temp.length() - 1);
				hashMap.put(StringUtils.obj2Str(parName), temp);
			}
		}
		return hashMap;
	}
	
	public static String param2Map(Map<String,Object> dataMap) {
		Iterator<?> it = dataMap.keySet().iterator();
		StringBuilder sb = new StringBuilder();
		while (it.hasNext()) {
			if(sb.length()>0)sb.append("&");
			String key = (String) it.next();
			String val = String.valueOf(dataMap.get(key));
			val = CryptUtils.urlEncode(val);
			sb.append(key).append("=").append(val);
		}
		return sb.toString();
	}
	
	/**
	 * 获取访问地 地址(包括代理的IP)
	 * @param request
	 * @return
	 */
	public static String getRemoteIp(HttpServletRequest request){
	    String ip = request.getHeader("x-forwarded-for");
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("Proxy-Client-IP");
	    }  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getRemoteAddr();
	    }  
	    return ip;  
	}
	/**
	 * 获取客户端浏览器信息
	 * @param req
	 * @return
	 */
	public static UserAgentInfo getUserAgentInfo(HttpServletRequest req){
		String userAgent = req.getHeader("user-agent");
		UserAgentInfo info = new UserAgentInfo();
		if(userAgent==null)return info;
		//操作系统
		if(userAgent.contains("Linux")){
			info.setOs(UserAgentInfo.CLIENT_OS_LINUX);
			info.setOsName("linux");
		}else if(userAgent.contains("Mac")){
			info.setOs(UserAgentInfo.CLIENT_OS_MAC);
			info.setOsName("mac");
		}else if(userAgent.contains("Windows")){
			info.setOs(UserAgentInfo.CLIENT_OS_WIN);
			info.setOsName("windows");
		}
		
		//子系统
		if(userAgent.contains("iPhone")){
			info.setOs(UserAgentInfo.CLIENT_OS_IPHONE);
			info.setOsName("iphone");
			info.setMobile(true);
		}else if(userAgent.contains("Android")){
			info.setOs(UserAgentInfo.CLIENT_OS_ANDROID);
			info.setOsName("android");
			info.setMobile(true);
		}else if(userAgent.contains("iPad")){
			info.setOs(UserAgentInfo.CLIENT_OS_IPAD);
			info.setOsName("ipad");
			info.setMobile(true);
		}
		
		//那个APP
		if(userAgent.contains("LfcpClient")){
			info.setAppName("lfcp");
		}else if(userAgent.contains("MicroMessenger")){
			info.setAppName("weixin");
		}
		
		//浏览器
		if(userAgent.contains("Chrome")){
			info.setBrowser("chrome");
			info.setWebkit("webKit");
		}else if(userAgent.contains("Safari")){
			info.setBrowser("safari");
			info.setWebkit("webKit");
		}else if(userAgent.contains("Opera")){
			info.setBrowser("opera");
			info.setWebkit("webKit");
		}
		
		//子浏览器
		if(userAgent.contains("MQQBrowser")){
			info.setBrowserSub("MQQBrowser");
		}else if(userAgent.contains("sogoumobilebrowser")){
			info.setBrowserSub("sogoumobilebrowser");
		}
		info.setRemoteIp(getRemoteIp(req));
		if(userAgent.contains("Mobile"))info.setMobile(true);
		return info;
	}
	
	public static void printRequestHeader(HttpServletRequest req){
		JSONObject data = new JSONObject();
		Enumeration<String> names = req.getHeaderNames();
		while(names.hasMoreElements()){
			String name = names.nextElement();
			String value = req.getHeader(name);
			data.put(name,value);
		}
		logger.info("Header: "+req.getRequestURI()+"====>"+JsonUtils.toStringNoEx(data));
	}
	
	public static void printRequest(HttpServletRequest req){
		JSONObject data = new JSONObject();
		Enumeration<String> names = req.getParameterNames();
		while(names.hasMoreElements()){
			String name = names.nextElement();
			String value = req.getParameter(name);
			data.put(name,value);
		}
		logger.info("request: "+req.getRequestURI()+"====>"+JsonUtils.toStringNoEx(data));
	}

	public static void printAttributeNames(HttpServletRequest req){
		Enumeration<String> names = req.getAttributeNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			Object str = req.getAttribute(name);
			logger.error(name+" >>>> "+str.toString());
		}
	}

	/**
	 * 发送对象
	 * @param req
	 * @param res
	 * @param result
	 */
	public static void sendError(ServletRequest req, ServletResponse res, JsonResult result){
		try {
			res.resetBuffer();
			res.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
			res.getWriter().write(JsonUtils.toString(result));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static String buildUrl(String currentUrl,String attrs){
		currentUrl = CryptUtils.urlDecode(currentUrl, "utf-8");
		String[] items = StringUtils.split(currentUrl,"?");
		if(items.length==1){
			return currentUrl+"?"+attrs;
		}else{
			return currentUrl+"&"+attrs;
		}
	}
}
