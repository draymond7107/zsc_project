package com.zsc.base.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public final class NumberUtils extends org.apache.commons.lang.math.NumberUtils{
	/**比较两个整数**/
	public final static boolean equals(Integer obj,Integer val){
		return obj!=null && obj.equals(val);
	}
	/**比较两个浮点**/
	public final static boolean equals(Double obj,Double val){
		return obj!=null && obj.equals(val);
	}
	/**
	 * Boolean 型的字符串True || 1 转 Boolean
	 * @param obj 类型的字符窜
	 * @return 返回Boolean
	 */
	public final static boolean str2Boolean(Object obj) {
		if (obj == null){
			return false;
		}
		if (!(obj instanceof String)){
			return false;
		}
		String str = obj.toString() + "";
		str = str.trim();
		if (str.equals("true") || str.equals("True") || str.equals("1")) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 数组转字符串
	 * @param arr
	 * @param split
	 * @return
	 */
	public final static String arr2Str(int[] arr,String split){
		StringBuilder sb = new StringBuilder();
		for(Object obj:arr){
			sb.append(String.valueOf(obj)).append(split);
		}
		return sb.substring(0,sb.length()-split.length());
	}
	/**
	 * 字符串转整数型 默认是0
	 * @param s
	 * @return
	 */
	public final static int str2Int(String str) {
		return str2Int(str, 0);
	}
	public final static int str2Int(String str, int defaultRef) {
		try {
			return Integer.parseInt(str);
		} catch (Exception ex) {
			return defaultRef;
		}
	}
	/**
	 * 字符串转长整数 默认是0
	 * @param str
	 * @return
	 */
	public final static long str2Long(String str) {
		return str2Long(str, 0);
	}
	public final static long str2Long(String str, long defaultRef) {
		try {
			return Long.parseLong(str);
		} catch (Exception ex) {
			return defaultRef;
		}
	}
	/**
	 * type必须指定是舍去还是进位
	 * @param num
	 * @param len
	 * @param type BigDecimal.ROUND_HALF_DOWN,BigDecimal.ROUND_HALF_UP,BigDecimal.ROUND_DOWN
	 * @return
	 */
	public final static String formatDouble(double num,int len,int type){
		BigDecimal b = new BigDecimal(num);
		return String.valueOf(b.setScale(len,type).doubleValue());
	}
	public final static byte[] str2ByteArr(String str) {
    	str = str.trim();
    	int len = 0;
    	byte[] arr = new byte[str.length()];
        for (int i = 0; i < str.length(); i++) {
        	String tmp = str.substring(i,i + 1);
        	if(!StringUtils.trim(tmp).isEmpty()){
        		arr[i] = Byte.parseByte(str.substring(i, i + 1));
        		len++;
        	}
        }
        byte[] ret = new byte[len];
        System.arraycopy(arr,0,ret,0,len);
        return ret;
    }
	/**
	 * 把字符串变成数组
	 * @param str
	 * @return
	 */
	public final static String[] str2StrArr(String str) {
    	str = str.trim();
    	int len = 0;
    	String[] arr = new String[str.length()];
        for (int i = 0; i < str.length(); i++) {
        	String tmp = str.substring(i,i + 1);
        	if(!StringUtils.trim(tmp).isEmpty()){
        		arr[i] = str.substring(i, i + 1);
        		len++;
        	}
        }
        String[] ret = new String[len];
        System.arraycopy(arr,0,ret,0,len);
        return ret;
    }
	/**
	 * 数字字符串转int数组  12345>[1,2,3,4,5]
	 * 非数字直接忽略
	 * @param str
	 * @return
	 */
	public final static int[] str2IntArr(String str) {
		return str2IntArr(str,-1);
	}
    public final static int[] str2IntArr(String str,int defInt) {
    	str = str.trim();
    	int len = 0;
    	int[] arr = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
        	String tmp = str.substring(i,i + 1);
        	if(!StringUtils.trim(tmp).isEmpty()){
        		arr[len] = str2Int(tmp,defInt);
        		len++;
        	}
        }
        int[] ret = new int[len];
        System.arraycopy(arr,0,ret,0,len);
        return ret;
    }
    /**
	 * 字符串转double 默认是0
	 * @param str
	 * @return
	 */
	public final static double str2Double(String str) {
		return str2Double(str, 0);
	}
	public final static double str2Double(String str, double defaultRef) {
		try {
			return Double.parseDouble(str);
		} catch (Exception ex) {
			return defaultRef;
		}
	}
	/**
	 * 数字字符串转int数组  1,2,3,4,5>[1,2,3,4,5]
	 * @param str
	 * @return
	 */
    public final static int[] str2IntArrSplit(String str) {
        String[] str_arr = str.split("[^\\d]+");
        return strArr2IntArr(str_arr);
    }
    public final static int[] strArr2IntArr(String[] str_arr) {
        int[] bytes = new int[str_arr.length];
        int c = 0;
        for (int i = 0; i < bytes.length; i++) {
            if (StringUtils.isNotEmpty(str_arr[i])) {
                bytes[c] = Integer.parseInt(str_arr[i]);
                c++;
            }
        }
        int[] ret = new int[c];
        System.arraycopy(bytes, 0, ret, 0, c);
        return ret;
    }
    public final static Integer[] str2IntegerArrSplit(String str){
    	 String[] str_arr = str.split("[^\\d]+");
    	 Integer[] intarr = new Integer[str_arr.length];
        for (int i = 0; i < str_arr.length; i++) {
        	intarr[i] = Integer.parseInt(str_arr[i]);
        }
        return intarr;
    }
	/**
	 * 64进制字符串转long型
	 * @param strId
	 * @return
	 */
	public final static long str64long(String strId) {
		String sq = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ#$";
		long id = 0;
		for (int i = 0; i < strId.length(); i++) {
			char w = strId.charAt(i);
			int m = strId.length() - 1 - i;
			if (m > 0)
				id = id + sq.indexOf(w)*((long) Math.pow(sq.length(), m));
			else
				id = id + sq.indexOf(w);
		}
		return id;
	}
	/**
	 * 64进制long型转字符串
	 * @param strId
	 * @return
	 */
	public final static String long64str(long strId) {
		String sq = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ#$";
		char[] digits = sq.toCharArray();
		char[] buf = new char[sq.length()];
		int charPos = sq.length()-1;
		do {
			buf[--charPos] = digits[(int)(strId&63)];
			strId >>>= 6;
		} while (strId != 0);
		return new String(buf,charPos,(63-charPos));
	}
	/**
	 * 32进制long型转字符串
	 * @param strId
	 * @return
	 */
	public final static String long32str(long strId) {
		String sq = "0123456789ABCDEFGHJKMNPQRSTUVWXY";
		int len = sq.length();
		char[] digits = sq.toCharArray();
		char[] buf = new char[len];
		int charPos = len - 1;
		do {
			buf[--charPos] = digits[(int) (strId & (len - 1))];
			strId >>>= 5;
		} while (strId != 0);
		return new String(buf, charPos, (len - 1 - charPos));
	}
	/**
	 * 32进制字符串转long型 大小写不敏感
	 * @param strId
	 * @return
	 */
	public final static long str32long(String strId) {
		String sq = "0123456789ABCDEFGHJKMNPQRSTUVWXY";
		strId = strId.toUpperCase();
		long id = 0;
		for (int i = 0; i < strId.length(); i++) {
			char w = strId.charAt(i);
			int m = strId.length() - 1 - i;
			if (m > 0)
				id = id + sq.indexOf(w) * ((long) Math.pow(sq.length(), m));
			else
				id = id + sq.indexOf(w);
		}
		return id;
	}
	/**
	 * 判断String是否是整数
	 * @param str
	 * @return
	 */
	public final static boolean isInt(String str){   
		if(str!= null) return str.matches("^[0-9]+$");else return false;   
	}
	/**
	 * 判断字符串是否为一个数字,包括是一个双精度
	 * @param str
	 * @return
	 */
	public final static boolean isDouble(String str){
		if(str==null || str.length()==0 || str.equals(".")) {
			return false;
		}
		Pattern pattern = Pattern.compile("-?[0-9]*\\.?[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
	/**
	 * 是否是数字
	 * @param str
	 * @return
	 */
	public final static boolean isNumber(String str){
		return isInt(str)||isDouble(str);
	}
	/**
	 * 转到16进制写法
	 * @param num
	 * @return
	 */
	public final static String toHexString(int num){
		return "0x"+Integer.toHexString(num);
	}
	/**
	 * 格式化double金额
	 * @param d 数据值
	 * @param len 长度
	 * @param type 类别 @seee BigDecimal.ROUND_HALF_UP,BigDecimal.ROUND_HALF_DOWN等
	 * @return
	 */
	public final static double resetDouble(double d, int len, int type) {
		d = d * 100 / 100.00;
		//这里有BUG,如果44.165传入,生成四舍五入就是44.16,用String就行
		BigDecimal b = new BigDecimal(String.valueOf(d));
		return b.setScale(len, type).doubleValue();
	}
	//对double类型的金钱数格式化,两位小数 已经进行了四舍五入
	public final static double double2MoneyUp(double d) {
		return resetDouble(d,2,BigDecimal.ROUND_HALF_UP);
	}
	public final static double double2MoneyDown(double d) {
		return resetDouble(d,2,BigDecimal.ROUND_DOWN);
	}
	/**
	 * 字符串转 金钱格式 默认是
	 * @param str
	 * @return
	 */
	public final static double str2MoneyUp(String str) {
		return str2MoneyUp(str,0.00);
	}
	public final static double str2MoneyUp(String str, double defaultRef) {
		try {
			return double2MoneyUp(Double.parseDouble(str));
		} catch (Exception ex) {
			return double2MoneyUp(defaultRef);
		}
	}
	//小数点后直接舍去
	public final static String formatMoneyDown(double d){
		return formatMoney(d,false);
	}
	//格式化金额,需要指定是舍去还是四舍五入 true是四舍五入
	public final static String formatMoney(double d,boolean upOrDown) {
		return formatMoney(d,2,upOrDown?BigDecimal.ROUND_HALF_UP:BigDecimal.ROUND_DOWN);
	}
	//四舍五入
	public final static String formatMoney(double num,int len,int type){
		double money = resetDouble(num,len,type);
		try {
			String format = "0.00";
			if(len==1)format="0.0";
			if(len==2)format="0.00";
			if(len==3)format="0.000";
			if(len==4)format="0.0000";
			if(len==5)format="0.00000";
			if(len==6)format="0.000000";
			return new DecimalFormat(format).format(money);
		} catch (Exception e){
			return String.valueOf(money);
		}
	}
	/**
	 * 格式化本地金额,带分隔符符号
	 * @param moneyStr
	 * @return
	 */
	public static String formatLocalMoneyDown(String moneyStr,int len){
		try {
			if(moneyStr == null) return "";
			if(!isNumber(moneyStr)) return "";
			BigDecimal b = new BigDecimal(moneyStr);
			double money = b.setScale(len,BigDecimal.ROUND_DOWN).doubleValue();
			String format = "###,###.00";
			if(len==1)format="###,###.0";
			if(len==2)format="###,###.00";
			if(len==3)format="###,###.000";
			if(len==4)format="###,###.0000";
			DecimalFormat decimalFormat = new DecimalFormat(format);
			String num = decimalFormat.format(money);
			if(len==0 && num.indexOf(".")>-1){
				return num.substring(0,num.indexOf("."));
			}
			return num;
		}catch (Exception e){
			return "-";
		}
	}
	public static String shortMoney(String numberStr){
        if(numberStr==null || numberStr.isEmpty())return "";
        double number = toDouble(numberStr,0);
        return shortMoney(number);
    }
    public static String shortMoney(Number number){
        if(number==null)return "";
        return shortMoney(number.doubleValue());
    }
    public static String shortMoney(double number){
        StringBuilder sb = new StringBuilder("");
        if(number>=100000000)sb.append((int)(number/100000000)).append("亿");
        number = number%100000000;
        if(number>=10000)sb.append((int)(number/10000)).append("万");
        return sb.toString();
    }
	public static String yiAndWan(String money){
		if(StringUtils.isEmpty(money) || money.equals("0") || money.equals("0.0") || money.equals("0.00"))
			return "0";
		if(money.contains(",") || money.contains("，"))
			money = money.replaceAll(",","").replaceAll("，","");
		if(!NumberUtils.isNumber(money)){
			return "0";
		}
		double moneyNum = NumberUtils.str2Double(money,-1);
		if(moneyNum < 0) return "0";
		double YIYUAN = 100000000D;
		String retMoney = "0";
		//大于1亿(取两位小数)
		if(moneyNum >= YIYUAN){
			retMoney = NumberUtils.resetDouble(moneyNum / YIYUAN,2,BigDecimal.ROUND_DOWN) + "亿";
		}else if(moneyNum >= 10000D){
			retMoney = (int)(moneyNum / 10000) + "万";
		}else{
			retMoney = (int)moneyNum + "";
		}
		return retMoney;
	}
	public static void main(String[] args) throws Exception{
		System.out.println(formatMoney(44.1,false));
	}

	/**
	 * 小int转大int数组
	 * @param arr
	 * @return
	 */
	public static Integer[] intArr2IntegerArr(int[] arr){
		Integer[] newArr = new Integer[arr.length];
		for(int i=0;i<arr.length;i++){
			newArr[i] = arr[i];
		}
		return newArr;
	}
}
