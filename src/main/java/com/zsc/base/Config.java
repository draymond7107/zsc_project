package com.zsc.base;

import com.zsc.base.utils.FileUtils;
import com.zsc.base.utils.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.util.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author icelove
 */
public class Config{
	private final static Log logger = LogFactory.getLog(Config.class);
	public final static String DEFAULT_CONTENT_TYPE = "text/html;charset=UTF-8";
	public final static String LOCAL_PATH_PREFIX = "file://";
	public final static String classPath = Config.class.getResource("/").toString();
	/** 编码信息* */
	public final static String ENC_UTF = "UTF-8";
	public final static String ENC_8859_1 = "8859_1";
	public final static String ENC_GBK = "gbk";
	private static Properties properties;
	
	static{
		String iceloveProps = "application.properties";
		properties = loadProperties(iceloveProps);
		if(properties==null){
			//防止后续业务失败,这里创建对象
			logger.error("Config init failure....");
			properties = new Properties();
		}
	}

	public static void main(String[] args){
		System.out.println(getProperty("http.userAgent"));
	}
	/**
	 * 多位置查询配置文件路径
	 * @param propName
	 * @return
	 */
	public static Properties loadProperties(String propName){
		Properties properties = new Properties();
		boolean loaded = loadProperties(properties,"." + File.separatorChar + propName) || 
				loadProperties(properties,Config.class.getResourceAsStream("/WEB-INF/" + propName)) || 
				loadProperties(properties,Config.class.getResourceAsStream("/" + propName));
		if(!loaded){
			return null;
		}
		return properties;
	}

	/**
	 * 初始化配置文件
	 * @param props
	 * @param path
	 * @return
	 */
	public static boolean loadProperties(Properties props,String path){
		try{
			File file = new File(path);
			if(file.exists() && file.isFile()){
				props.load(new FileInputStream(file));
				logger.error("Config找到的类地址：" + file.getAbsolutePath());
				return true;
			}
		}catch(Exception ignore){}
		return false;
	}

	/**
	 * 初始化配置文件
	 * @param props
	 * @param is
	 * @return
	 */
	private static boolean loadProperties(Properties props,InputStream is){
		try{
			props.load(is);
			return true;
		}catch(Exception ignore){
		} finally{
			IOUtils.closeQuietly(is);
		}
		return false;
	}

	/**
	 * 获取配置文件的根路径
	 * @return
	 */
	public static String getRootConfigDir(){
		String rootConfig = getProperty("soft.root.config.dir");
		if(rootConfig == null || rootConfig.isEmpty()){
			rootConfig = FileUtils.getClassesPath();
		}
		return rootConfig;
	}

	/**
	 * 获取软件配置的域名地址
	 * @return
	 */
	public static String getSoftDomain(){
		return getProperty("soft.domain");
	}

	public static String getSoftDomainHttp(){
		return "http://" + getProperty("soft.domain");
	}

	public static String getSoftDomainHttps(){
		return "https://" + getProperty("soft.domain");
	}

	/**
	 * 返回软件的根目录地址 物理地址 如返回d:/temp 或者/usr/local/tomcat
	 * @return
	 */
	public static String getSoftRootPath(){
		return getProperty("soft.root.path");
	}

	/**
	 * 设置当前软件环境的物理地址
	 * @param path
	 */
	public static void setSoftRootPath(String path){
		properties.setProperty("soft.root.path",path);
	}

	/**
	 * 是否应用安全HTTP协议
	 * @return
	 */
	public static boolean useSSL(){
		return getBoolean("soft.useSSL");
	}

	/**
	 * 获取是否配置安全HTTP协议
	 * @return
	 */
	public static String getScheme(){
		return useSSL()?"https://":"http://";
	}

	public static boolean getBoolean(String name){
		String value = getProperty(name);
		if(StringUtils.isEmpty(value))return false;
		return Boolean.valueOf(value);
	}

	public static int getIntProperty(String name){
		String value = getProperty(name);
		try{
			return Integer.parseInt(value);
		}catch(NumberFormatException nfe){
			return -1;
		}
	}

	public static int getIntValue(String name,int def){
		String value = getProperty(name);
		try{
			return Integer.parseInt(value);
		}catch(NumberFormatException nfe){
			return def;
		}
	}

	public static int getIntProperty(String name,int fallbackValue){
		String value = getProperty(name,String.valueOf(fallbackValue));
		try{
			return Integer.parseInt(value);
		}catch(NumberFormatException nfe){
			return -1;
		}
	}

	public static long getLongProperty(String name){
		String value = getProperty(name);
		try{
			return Long.parseLong(value);
		}catch(NumberFormatException nfe){
			return -1;
		}
	}

	public static long getLongProperty(String name,long defaultVal){
		String value = getProperty(name);
		try{
			return Long.parseLong(value);
		}catch(NumberFormatException nfe){
			return defaultVal;
		}
	}

	public static String getProperty(String name){
		return getProperty(name,null);
	}

	public static String getProperty(String name,String defaultValue){
		String value = properties.getProperty(name);
		if(null == value){
			value = properties.getProperty(name + ".fallback");
		}
		if(value == null)
			value = defaultValue;
		return replace(value);
	}

	public static long getThreadWaitingTime(){
		return getLongProperty("thread.waiting.time",20L);
	}

	public static long getRequestHtmlTime(){
		return getLongProperty("request.html.waiting.time",100L);
	}

	public static int getHttpSocketTimeout(){
		return getIntProperty("http.socket.timeout");
	}

	public static int getHttpSocketTimeout(int httpSocketTimeout){
		return getIntProperty("http.socket.timeout",httpSocketTimeout);
	}

	private static String replace(String value){
		if(null == value){
			return value;
		}
		String newValue = value;
		int openBrace = 0;
		if(-1 != (openBrace = value.indexOf("{",openBrace))){
			int closeBrace = value.indexOf("}",openBrace);
			if(closeBrace > (openBrace + 1)){
				String name = value.substring(openBrace + 1,closeBrace);
				if(name.length() > 0){
					newValue = value.substring(0,openBrace) + getProperty(name) + value.substring(closeBrace + 1);
				}
			}
		}
		if(newValue.equals(value)){
			return value;
		}else{
			return replace(newValue);
		}
	}
}
