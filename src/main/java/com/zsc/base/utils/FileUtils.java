package com.zsc.base.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.net.URL;
/**
 * 使用这个类必须classPath下有config.properties
 * @author lujun
 *
 */
public class FileUtils{
	private final static Log logger = LogFactory.getLog(FileUtils.class);
	public final static String configFile = "application.properties";
	public static String CLASSES_PATH = null;
	public static String WEB_ROOT_PATH = null;
	public static String WORK_PATH = null;
	public static void pathShow(){
		logger.error("<ConfigPath>"+getUrl("application.properties"));
		logger.error("<WorkPath>"+getWorkPath());
		logger.error("<WebRootPath>"+getWebRootPath());
		logger.error("<ClassesPath>"+getClassesPath());
	}
	public static URL getUrl(String configFile){
		URL url = null;
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		url = loader.getResource(configFile);
		return url;
	}
	/**
	 * 获取位置  
	 * @return
	 */
	public static String getWorkPath(){
		//上级目录
		if(WORK_PATH!=null)return WORK_PATH;
		String wp = getWebRootPath();
		int idx = wp.lastIndexOf("/");
		WORK_PATH = wp.substring(0,idx);
		return WORK_PATH;
	}
	/**
	 * 获取位置  /WebRoot
	 * @return
	 */
	public static String getWebRootPath(){
		if(WEB_ROOT_PATH!=null)return WEB_ROOT_PATH;
		String wp = getClassesPath();
		WEB_ROOT_PATH = wp.replace("/WEB-INF/classes","");
		return WEB_ROOT_PATH;
	}
	/**
	 * 获取位置   /WEB-INF/classes
	 * @return
	 */
	public static String getClassesPath(){
		//String configFile = FileUtils.class.getClassLoader().getResource("/").toString();
		if(CLASSES_PATH!=null)return CLASSES_PATH;
		URL url = getUrl(configFile);
		if(url==null){
			throw new RuntimeException("no config find! please put "+configFile+" in your classpath");
		}
		//linux请求也是返回 file:/var/laifa-webapps/admin/webroot/ROOT/WEB-INF/classes/config.properties
		String classesPath = url.toString();
		classesPath = classesPath.replace(File.separator,"/").replace("application.properties","");
		if(classesPath.startsWith("file:/"))classesPath = classesPath.substring(5);
		if(classesPath.endsWith("/"))classesPath = classesPath.substring(0,classesPath.length()-1);
		CLASSES_PATH = classesPath;
		return CLASSES_PATH;
	}
	/**
	 * 必须要"/"开头 获取文件夹"/WEB-INF/classes/config" 位置
	 * /WebRoot/WEB-INF/classes/config/channel-config.xml
	 * @param filePath
	 * @return
	 */
	public static String getConfigFile(String filePath){
		return getClassesPath()+"/config"+filePath;
	}
}
