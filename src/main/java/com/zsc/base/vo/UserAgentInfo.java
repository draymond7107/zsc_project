package com.zsc.base.vo;
public class UserAgentInfo{
	public final static int CLIENT_OS_UNKNOW = 0;//未知来源
	
	public final static int CLIENT_OS_LINUX = 10;//LINUX
	public final static int CLIENT_OS_ANDROID = 11;//ANDROID
	
	public final static int CLIENT_OS_IOS = 20;//IOS
	public final static int CLIENT_OS_IPHONE = 21;//IPHONE
	public final static int CLIENT_OS_IPAD = 22;//IPAD
	public final static int CLIENT_OS_MAC = 23;//MAC
	
	public final static int CLIENT_OS_WIN = 30;//WIN
	
	public final static int CLIENT_OS_OTHER = 9;//其他
	private int os;			//Android,iOS,Linux,Windows
	private String osName;	//Android,iOS,Linux,Windows
	private String netType;	//WIFI
	private String appName;	//weixin
	private String webkit;	//AppleWebKit
	private String browser;	//Chrome Safari
	private String browserSub;//子类型浏览器
	private String remoteIp;
	//是否是手机
	private boolean mobile;
	public UserAgentInfo (){
		this.os = CLIENT_OS_UNKNOW;
		this.osName="";
		this.netType="";
		this.appName="";
		this.webkit="";
		this.browser="";
		this.browserSub="";
		this.remoteIp="";
	}

	public boolean isLinux(){
		return os==CLIENT_OS_LINUX || os/10==1;
	}
	public boolean isIos(){
		return os==CLIENT_OS_IOS || os/10==2;
	}
	public boolean isWindows(){
		return os==CLIENT_OS_WIN || os/10==3;
	}
	
	public boolean isAndroid(){
		return os==CLIENT_OS_ANDROID;
	}
	public boolean isIphone(){
		return os==CLIENT_OS_IPHONE;
	}
	public boolean isIpad(){
		return os==CLIENT_OS_IPAD;
	}

	public int getOs(){
		return os;
	}
	public void setOs(int os){
		this.os = os;
	}
	public void setOsName(String osName){
		this.osName = osName;
	}
	public String getOsName(){
		return osName;
	}
	public String getNetType(){
		return netType;
	}
	public void setNetType(String netType){
		this.netType = netType;
	}
	public String getAppName(){
		return appName;
	}
	public void setAppName(String appName){
		this.appName = appName;
	}
	public boolean isMobile(){
		return mobile;
	}
	public void setMobile(boolean mobile){
		this.mobile = mobile;
	}
	public String getWebkit(){
		return webkit;
	}
	public void setWebkit(String webkit){
		this.webkit = webkit;
	}
	public String getBrowser(){
		return browser;
	}
	public void setBrowser(String browser){
		this.browser = browser;
	}
	public String getBrowserSub(){
		return browserSub;
	}
	public void setBrowserSub(String browserSub){
		this.browserSub = browserSub;
	}
	public String getRemoteIp(){
		return remoteIp;
	}
	public void setRemoteIp(String remoteIp){
		this.remoteIp = remoteIp;
	}
}
