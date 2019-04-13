package com.zsc.base.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LogUtils{
	private static final Map<String, Boolean> LOG_ON_OFF = new HashMap<String, Boolean>();
	public static enum LOG_LEVEL {DEBUG,INFO,ERROR,WARN};
	public final static Log log = LogFactory.getLog(LogUtils.class);
	public final static boolean isDebug = log.isDebugEnabled();
	public final static boolean isInfo = log.isInfoEnabled();
	public final static boolean isError = log.isErrorEnabled();
	public final static String PRINT = "[LogUtils-PRINT]:";
	//获取开头名称
	public static final String getPreName(Object cls){
		String clsName = null;
        if(cls instanceof Class) {
            clsName = ((Class<?>) cls).getName();
        } else {
            clsName = cls.getClass().getName();
        }
        return clsName;
	}
	/**
	 * 按照类获取Log
	 * @param c
	 * @return
	 */
	public static final Log getLog(Class<?> c) {
		return LogFactory.getLog(c.getClass());
	}
	/**
	 * 获取默认的shop Log
	 * @return
	 */
	public static final Log getLog() {
		return log;
	}
	/**
	 * 直接控制台输出
	 * @param logStr
	 */
	public static void print(String logStr) {
		System.out.println(PRINT + logStr);
	}
	
	public static void log(Object cls, String str, int[] array) {
        String info = str + " array=";
        for (int val : array) {
            info += String.valueOf(val) + ",";
        }
        log(cls, info);
    }

    public static void log(Object cls, String... message) {
        String msg = StringUtils.join(message, "; ");
        log(cls, msg);
    }

    public static void log(Object cls, String message) {
    	if (!log.isInfoEnabled())return;
        String clsName = getPreName(cls);
        if(LOG_ON_OFF.get(clsName) != null && LOG_ON_OFF.get(clsName) == false) {
            return;
        }
        info(cls, message);
    }
    
    public static void print(Object cls,String logStr) {
    	String clsName = getPreName(cls);
		System.out.println("[" + clsName + "]-print " + logStr);
	}
	public static void debug(Object s) {
		if (log.isDebugEnabled())
			log.debug(s);
	}
	public static void debug(Object cls,Object s) {
		String clsName = getPreName(cls);
		if (log.isDebugEnabled()){
			log.debug("[" + clsName + "]:" + s);
		}
	}
	public static void info(Object s) {
		if (log.isInfoEnabled()) {
			log.info(s);
		}
	}
	public static void info(Object cls,Object s) {
		String clsName = getPreName(cls);
		if (log.isInfoEnabled()) {
			log.info("[" + clsName + "]:" + s);
		}
	}
	public static void error(Object error) {
		if (log.isErrorEnabled()) {
			log.error(error);
		}
	}
	public static void error(Object cls,Object s) {
		String clsName = getPreName(cls);
		if (log.isErrorEnabled()) {
			log.error("[" + clsName + "]:" + s);
		}
	}
	public static void warn(Object warn) {
		if (log.isWarnEnabled())
			log.warn(warn);
	}
	public static void warn(Object cls,Object s) {
		String clsName = getPreName(cls);
		if (log.isWarnEnabled()){
			log.warn("[" + clsName + "]:" + s);
		}
	}
    
	/**
	 * 记录日志方法体,保证日志绝对记录成功
	 * 保证日志绝对记录成功
	 * @param log
	 * @param format
	 * @param args
	 */
	public static void info(Log log,String format,Object ... args){
		format(log,LOG_LEVEL.INFO,format,args);
	}
	public static void error(Log log,String format,Object ... args){
		format(log,LOG_LEVEL.ERROR,format,args);
	}
	public static void format(Log log,LOG_LEVEL level,String format,Object ... args){
		String logInfo = "";
		try{
			logInfo = String.format(format,args);
			switch(level){
				case DEBUG:{
					if(log.isDebugEnabled()) log.debug(logInfo);
					break;
				}case INFO:{
					if(log.isInfoEnabled()) log.info(logInfo);
					break;
				}case ERROR:{
					if(log.isErrorEnabled()) log.error(logInfo);
					break;
				}case WARN:{
					if(log.isWarnEnabled()) log.warn(logInfo);
					break;
				}default:{
					if(log.isInfoEnabled()) log.info(logInfo);
					break;
				}
			}
		}catch (Exception e) {
			logInfo = format;
			for(Object obj:args){
				logInfo+=String.valueOf(obj);
			}
			log.error("日志写入出现异常:"+logInfo);
		}
	}
	/**
	 * 记录日志方法体,保证日志绝对记录成功
	 * 保证日志绝对记录成功
	 * @param log
	 * @param format
	 * @param args
	 */
	public static void infoJson(Log log,String pre,Object obj){
		json(log,LOG_LEVEL.INFO,pre,obj);
	}
	public static void errorJson(Log log,String pre,Object obj){
		json(log,LOG_LEVEL.ERROR,pre,obj);
	}
	public static void json(Log log,LOG_LEVEL level,String pre,Object obj){
		String logInfo = "";
		try{
			logInfo = pre+JsonUtils.toStringNoEx(obj);
			switch(level){
			case DEBUG:{
				if(log.isDebugEnabled()) log.debug(logInfo);
				break;
			}case INFO:{
				if(log.isInfoEnabled()) log.info(logInfo);
				break;
			}case ERROR:{
				if(log.isErrorEnabled()) log.error(logInfo);
				break;
			}case WARN:{
				if(log.isWarnEnabled()) log.warn(logInfo);
				break;
			}default:{
				if(log.isInfoEnabled()) log.info(logInfo);
				break;
			}
		}
		}catch (Exception e){
			logInfo = pre;
			log.error("日志写入出现异常:"+logInfo+obj.toString());
		}
	}

	/**
	 * 打印Map
	 * @param map
	 */
	public static void printMap(Map<?, ?> map) {
		Iterator<?> it = map.keySet().iterator();
		while (it.hasNext()) {
			Object key = it.next();
			Object values = map.get(key);
			if (values instanceof Object[]) {
				for (Object obj : (Object[]) values)
					log.info("pars>>>" + key.toString() + ":" + (String) obj);
			} else if (values instanceof String) {
				log.info(">>>" + key.toString() + ":" + (String) values);
			} else if (values instanceof Number) {
				log.info(">>>" + key.toString() + ":" + values);
			} else if (values instanceof Boolean) {
				log.info(">>>" + key.toString() + ":" + values);
			}
		}
	}
}
