package com.zsc.base.utils;
import java.util.UUID;

public class RandomUtils extends org.apache.commons.lang.math.RandomUtils{
	//所有字母
	private final static String sqe = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private final static String SQE_STRING = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	/**
	 * 获取随机数
	 * @param len 多少位
	 * @return
	 */
	public final static String randomNumberStr(int len){
		StringBuffer sb = new StringBuffer("");
		while(len>0){
			sb.append(RandomUtils.nextInt(10));
			len--;
		}
		return sb.toString();
	}
	/**
	 * 获取随机数
	 * @param max 最大值
	 * @return
	 */
	public final static int randomNumner(int max){
		return RandomUtils.nextInt(max);
	}
	/**
	 * 大小写混合的所有字符随机出来
	 * 0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ
	 * @param len
	 * @return
	 */
	public final static String randomLetter(int len){
		int strLen = sqe.length();
		StringBuffer sb = new StringBuffer("");
		while(len>0){
			int index = RandomUtils.nextInt(strLen);
			sb.append(String.valueOf(sqe.charAt(index)));
			len--;
		}
		return sb.toString();
	}
	/**
	 * 从大写字符串里面随机出来字符
	 * 0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ
	 * @param len
	 * @return
	 */
	public final static String randomUpper(int len){
		int strLen = SQE_STRING.length();
		StringBuffer sb = new StringBuffer("");
		String str = SQE_STRING.toUpperCase();
		while(len>0){
			int index = RandomUtils.nextInt(strLen);
			sb.append(String.valueOf(str.charAt(index)));
			len--;
		}
		return sb.toString();
	}
	/**
	 * 从小写字符串里面随机出来字符
	 * 0123456789abcdefghijklmnopqrstuvwxyz
	 * @param len
	 * @return
	 */
	public final static String randomLower(int len){
		int strLen = SQE_STRING.length();
		StringBuffer sb = new StringBuffer("");
		String str = SQE_STRING.toLowerCase();
		while(len>0){
			int index = RandomUtils.nextInt(strLen);
			sb.append(String.valueOf(str.charAt(index)));
			len--;
		}
		return sb.toString();
	}
	public final static String uuid(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}
