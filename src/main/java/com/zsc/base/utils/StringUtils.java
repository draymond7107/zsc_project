package com.zsc.base.utils;
import com.alibaba.fastjson.JSONException;
import com.zsc.base.Config;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Set;

/**
 * 字符串工具集合
 * @author icelove
 */
public final class StringUtils extends org.apache.commons.lang.StringUtils {
	private final static String enReg = "^\\w+$";
	public static String getFileExtend(String fn) {
		if (isEmpty(fn))
			return null;
		int idx = fn.lastIndexOf('.') + 1;
		if (idx == 0 || idx >= fn.length())
			return null;
		return fn.substring(idx);
	}

	/**
	 * 检测字符是否为null
	 * @param str
	 * @return 空返回"" 否则返回源字符串
	 */
	public static String checkStr(String str) {
		return str == null || str.length()==0 || str.equals("null") ? "" : str.trim();
	}
	/**
	 * 检测Object是否为null
	 * @param obj
	 * @return 空返回"" 否则返回源字符串
	 */
	public static boolean checkObj(Object obj) {
		return obj == null || obj.equals("null");
	}
	/**
	 * 处理带HTML标记字符串
	 * @param str
	 * @return
	 */
	public static String formatNotHtml(String str) {
		if (str == null) return "";
		String randomStr = String.valueOf(System.currentTimeMillis());
		String html = replace(str, "&nbsp;", randomStr);
		html = replace(html, "\"", "&quot;");
		html = replace(html, "\t", "&nbsp;&nbsp;");// 替换跳格
		html = replace(html, "<", "&lt;");
		html = replace(html, ">", "&gt;");
		return replace(html, randomStr, "&nbsp;").trim();
	}

	/**
	 * 转换&lt; 成 < 简单的HTML语言
	 * @param str
	 * @return
	 */
	public static String formatHtml(String str) {
		if (str == null) return "";
		String randomStr = String.valueOf(System.currentTimeMillis());
		String html = replace(str, "&nbsp;", randomStr);
		html = replace(html, "&amp;", "&");
		html = replace(html, "&apos;", "'");
		html = replace(html, "&quot;", "\"");
		html = replace(html, "&nbsp;&nbsp;", "\t");// 替换跳格
		html = replace(html, "&nbsp;", " ");// 替换空格
		html = replace(html, "&lt;", "<");
		html = replace(html, "&gt;", ">");
		html =  replace(html, "&#34;", "\"");
		html =  replace(html, "&#60;", "<");
		html =  replace(html, "&#62;", ">");
		return replace(html, randomStr, "&nbsp;").trim();
	}
	/**
	 * 删除空行
	 * @param text
	 * @return
	 */
	public static String delBlankLine(String text){
		if(text==null || text.length()==0)return "";
		text = replace(replace(text,"\r",""),"\n","");
		return text;
	}
	/**
	 * 抽取纯文本信息
	 * @param inputHtml
	 * @return
	 */
//	public static String extractText(String inputHtml) throws Exception {
//		if(inputHtml==null)return "";
//		return Jsoup.parse(inputHtml).text();
//	}
	/**
	 * 检测一对字符串数组 是否有空字符 包括null和""
	 * @param str
	 * @return
	 */
	public static boolean arrayItemIsEmpty(String[] str) {
		if (str == null) {
			return true;
		}
		for (int i = 0; i < str.length; i++) {
			if (isEmpty(str[i])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 检测一对字符串数组 所有数据 是否 都未 空字符 包括null和""
	 * @param str
	 * @return
	 */
	public static boolean arrayItemHaveValue(String[] str) {
		if (str == null) {
			return false;
		}
		for (int i = 0; i < str.length; i++) {
			if (!isEmpty(str[i])) {
				return true;
			}
		}
		return false;
	}
	/**
	 * Object 转 String
	 * @param obj
	 * @return 空返回"" 否则返回源字符串
	 */
	public static String obj2Str(Object obj) {
		return obj == null || !(obj instanceof String) ? "" : obj.toString().trim();
	}
	/**
	 * 转到UTF编码
	 * @param str
	 * @return
	 */
	public static String toHex(String str){
		if(isEmpty(str))return "";
		String msg = "";
		char[] chars = str.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			msg += "\\u" + Integer.toHexString((int) chars[i]);
		}
		return msg;
	}
	
	/**
	 * double 字符串转整数
	 * @param str
	 * @return
	 */
	public static String formatString(String str) {
		str = str + "";
		if (str.indexOf(".") > 0) {
			str = str.substring(0,str.indexOf("."));
		}
		return str;
	}
	/**
	 * 把一串数字字符串用符号分割开来
	 * @param str
	 * @param split
	 * @return
	 */
	public static String numberSplit(String str,String split){
		if(str==null)return "";
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<str.length();i++){
			sb.append(str.charAt(i));
			if(i<str.length()-1)sb.append(split);
		}
		return sb.toString();
	}
	/**
	 * 把字符串分割成数组
	 * @param str
	 * @return
	 */
	public static String[] number2Arr(String str){
		if(str==null)return new String[0];
		String[] arr = new String[str.length()];
		for(int i=0;i<str.length();i++){
			arr[i] = String.valueOf(str.charAt(i));
		}
		return arr;
	}
	/**
	 * 根据非数字分割符号进行字符串分割
	 * @param str
	 * @return
	 */
	public static String[] str2ArrSplit(String str){
		String[] str_arr = str.split("[^\\d]+");
		return str_arr;
	}
	/**
	 * 数组转换成字符串
	 * @param "默认以,分割"
	 * @return
	 */
	public static String strArray2Str(String[] data, String fenge) {
		if (data == null) return "";
		if (fenge == null || fenge.equals("")) fenge = ",";
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < data.length; i++) {
			sb.append(data[i]).append(fenge);
		}
		if (data.length > 0) {
			return sb.substring(0, sb.length() - 1);
		}
		return sb.toString();
	}
	/**
	 * 整数数组转换成字符串不带分隔符
	 * @param data
	 * @return
	 */
	public static String intArrayToStr(int[] data){
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < data.length; i++) {
			sb.append(data[i]);
		}
		return sb.toString();
	}
	/**
	 * 整数数组转换成字符串()
	 * @param data
	 * @param fenge
	 * @return
	 */
	public static String intArrayToStr(int[] data,String fenge){
		if (data == null) return "";
		if (fenge == null || fenge.equals("")) fenge = ",";
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < data.length; i++) {
			sb.append(data[i]).append(fenge);
		}
		if (data.length > 0) {
			return sb.substring(0, sb.length() - 1);
		}
		return sb.toString();
	}
	/**
	 * 任意的数字转字符串
	 * @param array
	 * @param fenge
	 * @return
	 */
	public static String array2Str(Object[] array,String fenge){
		if (array == null) return "";
		if (fenge == null || fenge.equals("")) fenge = ",";
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < array.length; i++) {
			sb.append(array[i]).append(fenge);
		}
		if (array.length > 0) {
			return sb.substring(0, sb.length() - 1);
		}
		return sb.toString();
	}
	/**
	 * 合并Set集合
	 * @param set
	 * @param split
	 * @return
	 */
	public static String join(Set<String> set,String split){
		StringBuffer sb = new StringBuffer();
		for(String str:set){
			sb.append(str).append(split);
		}
		if(sb.length()-split.length()<0)return sb.toString();
		return sb.substring(0,sb.length()-split.length());
	}
	/**
	 * 查询中英文字符串长度
	 * 这个方式不是很妥当的,用GBK一个汉子是两个字节,用utf8就不行的
	 * @param str
	 * @return
	 */
	public static int strLen(String str){
		if(str!=null){
			int num;
			try{
				num = str.getBytes(Config.ENC_GBK).length;
				return num/2+num%2;
			}catch(UnsupportedEncodingException e){
				e.printStackTrace();
			}
		}
		return 0;
	}
	/**
	 * 字符长度截取，两个字母算一个中文
	 * @param str
	 * @param len
	 * @return
	 */
	public static String cutStr(String str, int len) {
		if (str == null || str.equals("")) {
			return "";
		} else if (len > str.length()) {
			return str;
		}
		len = len * 2;
		int cutNum = 0;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			int code = (int) c;
			if (code > 256) {
				cutNum += 2;
			} else
				cutNum++;
			if (cutNum > len) {
				return str.substring(0, i).toString().trim();
			}
		}
		return str;
	}

	public static boolean isEnStr(String str) {
		return str.matches(enReg);
	}
	/**
	 * 输入的字符是否是汉字
	 * @param a char
	 * @return boolean
	 */
	public static boolean isChinese(char a) {
	     int v = (int)a;
	     return (v >=19968 && v <= 171941);
	}
	/**是否包含中文字符**/
	public static boolean containsChinese(String s) {
		if (null == s || "".equals(s.trim()))
			return false;
		for (int i = 0; i < s.length(); i++) {
			if (isChinese(s.charAt(i)))
				return true;
		}
		return false;
	}
	/**
	 * 判断是不是一个合法的电子邮件地址
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		if (email == null) return false;
		email = email.trim();
		if (email.indexOf(' ') != -1) return false;
		int idx = email.indexOf('@');
		if (idx == -1 || idx == 0 || (idx + 1) == email.length()) return false;
		if (email.indexOf('@', idx + 1) != -1) return false;
		if (email.indexOf('.') == -1) return false;
		return true;
		/*
		 * Pattern emailer; if(emailer==null){ String check =
		 * "^([a-z0-9A-Z]+[-|\\._]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		 * emailer = Pattern.compile(check); } Matcher matcher =
		 * emailer.matcher(email); return matcher.matches();
		 */
	}
	/**
	 * 检测是否是电话号码
	 * @param phone
	 * @return
	 */
	public static boolean isPhone(String phone) {
		if (phone != null) {
			phone = phone.trim();
			String regex = "^0\\d{2,3}-*\\d{7,8}-*\\d*$";
			return phone.matches(regex);
		}
		return false;
	}

	/**
	 * 检测是否是手机号码
	 * 这个是初步检查判断
	 * @param mobile
	 * @return
	 */
	public static boolean isMobile(String mobile) {
		if(StringUtils.isEmpty(mobile))return false;
		if(mobile.length()<11)return false;
		if(mobile.startsWith("+86"))mobile = mobile.substring(3);
		if(mobile.length()!=11 || !mobile.startsWith("1"))return false;
		if(mobile.startsWith("12") || mobile.startsWith("16"))return false;
		if(!mobile.matches("1(2|3|4|5|6|7|8|9)[0-9]{9}"))return false;
		return true;
	}
	/**
	 * 判断字符串数组中是否含有指定字符串
	 * @param strs
	 * @param str
	 * @return
	 */
	public static boolean stringInArray(String[] strs, String str) {
		if (isEmpty(str) || strs == null) {
			return false;
		}
		for (int i = 0; i < strs.length; i++) {
			if (!isEmpty(strs[i]) && strs[i].equalsIgnoreCase(str)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 判断字符串是否是一个IP地址
	 * @param addr
	 * @return
	 */
	public static boolean isIPAddr(String addr) {
		if (isEmpty(addr))
			return false;
		String[] ips = split(addr, ".");
		if (ips.length != 4)
			return false;
		try {
			int ipa = Integer.parseInt(ips[0]);
			int ipb = Integer.parseInt(ips[1]);
			int ipc = Integer.parseInt(ips[2]);
			int ipd = Integer.parseInt(ips[3]);
			return ipa >= 0 && ipa <= 255 && ipb >= 0 && ipb <= 255 && ipc >= 0 && ipc <= 255 && ipd >= 0 && ipd <= 255;
		} catch (Exception e) {}
		return false;
	}
	/**
	 * 判断一个字符是否是GBK
	 */
	public static boolean isGBK(String str) {
		try {
			byte[] bytes = str.replace('?', 'a').getBytes(Config.ENC_8859_1);
			for (int i = 0; i < bytes.length; i++) {
				if (bytes[i] == 63) {
					return true;
				}
			}
		} catch (UnsupportedEncodingException e) {
			return false;
		}
		return false;
	}
	/**
	 * 大小写无关的字符串替换策略
	 * @param str
	 * @param src
	 * @param obj
	 * @return
	 */
	public static String replaceIgnoreCase(String str, String src, String obj) {
		String l_str = str.toLowerCase();
		String l_src = src.toLowerCase();
		int fromIdx = 0;
		StringBuffer result = new StringBuffer();
		do {
			int idx = l_str.indexOf(l_src, fromIdx);
			if (idx == -1)
				break;
			result.append(str.substring(fromIdx, idx));
			result.append(obj);
			fromIdx = idx + src.length();
		} while (true);
		result.append(str.substring(fromIdx));
		return result.toString();
	}
	/**
	 * 用户名必须是数字或者字母的结合
	 * @param username
	 * @return
	 */
	public static boolean isLegalUsername(String username) {
		for (int i = 0; i < username.length(); i++) {
			char ch = username.charAt(i);
			if (!isAscii(ch) && ch != '.' && ch != '_' && ch != '-' && ch != '+' && ch != '(' && ch != ')' && ch != '*' && ch != '^' && ch != '@' && ch != '%' && ch != '$' && ch != '#' && ch != '~' && ch != '-')
				return false;
		}
		return true;
	}
	/**
	 * 判断是否是字母和数字的结合
	 * @param name
	 * @return
	 */
	public static boolean isAsciiOrDigit(String name) {
		for (int i = 0; i < name.length(); i++) {
			char ch = name.charAt(i);
			if (!isAscii(ch))
				return false;
		}
		return true;
	}
	public static boolean isAscii(char ch) {
		return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9');
	}
	/**
	 * Map转json
	 * @param map
	 * @return
	 * @throws JSONException 
	 */
	public static String map2Json(Map<String,?> map) throws JSONException{
		if(map==null || map.size()==0)return "";
		return JsonUtils.toString(map);
	}
	/**
	 * 是否包含该字符串(不区分大小写)
	 * @param source 源字符串
	 * @param matchStr	被包含的字符串
	 * @return
	 */
	public static boolean containsIgnoreCase(String source,String matchStr){
		if(source==null || matchStr==null){
			return false;
		}else if(source.contains(matchStr) || source.toLowerCase().contains(matchStr.toLowerCase())){
			return true;
		}
		return false;
	}
	/**
	 * 合并数组
	 * @param arrays
	 * @return
	 */
	public static String[] createArray(String[] ...arrays){
		int len=0;
		for(int i=0;i<arrays.length;i++){
			len+=arrays[i].length;
		}
		String[] newArray = new String[len];
		int totalLen = 0;
		for(int i=0;i<arrays.length;i++){
			String[] array = arrays[i];
			System.arraycopy(array,0,newArray,totalLen,array.length);
			totalLen += array.length;
		}
		return newArray;
	}
	/**
	 * 删除空格
	 * @param str
	 * @return
	 */
	public static String deleteWhitespace(String str){
		if(isEmpty(str))return "";
		str = str.replaceAll("[^\u4E00-\u9FA5\u3000-\u303F\uFF00-\uFFEF\u0000-\u007F\u201c-\u201d]","").replaceAll("\\s","");
		return str;
	}
	
	public static void main(String[] args) {
		try{
			System.out.println("好的".getBytes("gbk").length);
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
	}
}
