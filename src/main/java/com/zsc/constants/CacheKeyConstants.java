package com.zsc.constants;
/**
 * 系统缓存定义的KEY
 * @author lujun
 *
 */
public interface CacheKeyConstants{
	/**客户端页面缓存**/
	String API_PAGE_CACHE = "API_HOME_INDEX_%s_%s_%s_%s_%s";
	//保存5分钟
	int API_PAGE_CACHE_TIME  = 5*60;
	
	String API_MISS_CACHE = "AWARD_NUMBER_%d_%d";
	
	/**用户下单缓存KEY**/
	String API_KEY_LAST_BUY	= "API_LAST_BUY_";
	
	/**用户注册IP**/
	String API_KEY_REG_IP	= "API_REG_IP_";
	
	/**锁定登陆账号**/
	String API_KEY_LOGIN_NAME	= "API_LOGIN_NAME_";
	
	/**手机号码绑定校验临时存放的KEY**/
	int API_KEY_VERIFY_MOBILE_TIME		= 1;
	String API_KEY_VERIFY_MOBILE		= "";



	/**(秒)保存的是3天时间**/
	int USER_ID_TIME		= 3*86400;
	String USER_ID_KEY		= "USER_%d";
	String USER_NAME_KEY	= "USERNAME_%s";

	/*用户openId缓存*/

	String USER_OPEN_ID_KEY	= "USEROPENID_%s";
	/*(秒)用户登录账户保存的是3天时间*/
	String USER_TOKEN_KEY	= "USERTOKEN_%s";
	int USER_TOKEN_TIME  = 3*86400;

	String ADMIN_OPEN_ID_KEY = "ADMINOPENID_%s";
	int ADMIN_TOKEN_TIME	 = 60;//1分钟
	
	//手机验证吗同IP发送次数
	String USER_MOBILE_SEND_COUNT = "SMS_SEND_COUNT_%s";
	//一个小时之内不能提交
	int USER_MOBILE_SEND_COUNT_TIME = 3600;
	
	//手机验证码缓存KEY
	String USER_MOBILE_CODE		= "KIND_%d_MCODE_%s";
	//验证码缓存时间(15分钟)
	int USER_MOBILE_CODE_TIME	= 15*60;
	
	//邮箱验证码缓存KEY
	String USER_EMAIL_CODE	= "KIND_%d_MCODE_%s";

}
