package com.zsc.base.utils;

import java.math.BigDecimal;

public class DoubleUtils {
	private static final int DEF_DIV_SCALE = 10;
	private DoubleUtils() {}
	/**
	 * 相加
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static double add(double d1, double d2) {
		BigDecimal b1 = new BigDecimal(Double.toString(d1));
		BigDecimal b2 = new BigDecimal(Double.toString(d2));
		return b1.add(b2).doubleValue();
	}
	public static double add(Double ... nums) {
		if(nums==null || nums.length==0 || nums[0]==null)throw new IllegalArgumentException();
		BigDecimal bd = new BigDecimal(nums[0].toString());
		for(int i=1;i<nums.length;i++){
			if(nums[i]==null){
				throw new IllegalArgumentException();
			}
			BigDecimal t = new BigDecimal(Double.toString(nums[i]));
			bd = bd.add(t);
		}
		return bd.doubleValue();
	}
	public static double add(String d1, String d2) {
		BigDecimal b1 = new BigDecimal(d1);
		BigDecimal b2 = new BigDecimal(d2);
		return b1.add(b2).doubleValue();
	}
	public static double add(String ... nums) {
		if(nums==null || nums.length==0 || nums[0]==null)throw new IllegalArgumentException();
		BigDecimal bd = new BigDecimal(nums[0].toString());
		for(int i=1;i<nums.length;i++){
			if(nums[i]==null){
				throw new IllegalArgumentException();
			}
			BigDecimal t = new BigDecimal(nums[i]);
			bd = bd.add(t);
		}
		return bd.doubleValue();
	}
	/**
	 * 相减
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static double sub(double d1, double d2) {
		BigDecimal b1 = new BigDecimal(Double.toString(d1));
		BigDecimal b2 = new BigDecimal(Double.toString(d2));
		return b1.subtract(b2).doubleValue();
	}
	public static double sub(String d1, String d2) {
		BigDecimal b1 = new BigDecimal(d1);
		BigDecimal b2 = new BigDecimal(d2);
		return b1.subtract(b2).doubleValue();
	}
	/**
	 * 相减 但是不能变负数
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static double subNotMinus(double d1, double d2) {
		if(d2>=d1)return 0.00;
		BigDecimal b1 = new BigDecimal(Double.toString(d1));
		BigDecimal b2 = new BigDecimal(Double.toString(d2));
		return b1.subtract(b2).doubleValue();
	}
	public static double sub(Double ... nums) {
		if(nums==null || nums.length==0 || nums[0]==null)throw new IllegalArgumentException();
		BigDecimal bd = new BigDecimal(nums[0].toString());
		for(int i=1;i<nums.length;i++){
			if(nums[i]==null){
				throw new IllegalArgumentException();
			}
			BigDecimal t = new BigDecimal(Double.toString(nums[i]));
			bd = bd.subtract(t);
		}
		return bd.doubleValue();
	}
	public static double sub(String ... nums) {
		if(nums==null || nums.length==0 || nums[0]==null)throw new IllegalArgumentException();
		BigDecimal bd = new BigDecimal(nums[0].toString());
		for(int i=1;i<nums.length;i++){
			if(nums[i]==null){
				throw new IllegalArgumentException();
			}
			BigDecimal t = new BigDecimal(nums[i]);
			bd = bd.subtract(t);
		}
		return bd.doubleValue();
	}
	/**
	 * 相乘
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static double mul(double d1, double d2) {
		BigDecimal b1 = new BigDecimal(Double.toString(d1));
		BigDecimal b2 = new BigDecimal(Double.toString(d2));
		return b1.multiply(b2).doubleValue();
	}
	public static double mul(Double ... nums) {
		if(nums==null || nums.length==0 || nums[0]==null)throw new IllegalArgumentException();
		BigDecimal bd = new BigDecimal(nums[0].toString());
		for(int i=1;i<nums.length;i++){
			if(nums[i]==null){
				throw new IllegalArgumentException();
			}
			BigDecimal t = new BigDecimal(Double.toString(nums[i]));
			bd = bd.multiply(t);
		}
		return bd.doubleValue();
	}
	public static double mul(String d1, String d2) {
		BigDecimal b1 = new BigDecimal(d1);
		BigDecimal b2 = new BigDecimal(d2);
		return b1.multiply(b2).doubleValue();
	}
	public static double mul(String ... nums) {
		if(nums==null || nums.length==0 || nums[0]==null)throw new IllegalArgumentException();
		BigDecimal bd = new BigDecimal(nums[0].toString());
		for(int i=1;i<nums.length;i++){
			if(nums[i]==null){
				throw new IllegalArgumentException();
			}
			BigDecimal t = new BigDecimal(nums[i]);
			bd = bd.multiply(t);
		}
		return bd.doubleValue();
	}
	/**
	 * 相除(默认保留10位小数)
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static double div(double d1, double d2) {
		return div(d1,d2,DEF_DIV_SCALE);
	}
	public static double div(double d1, double d2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("scale 不能小于0");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(d1));
		BigDecimal b2 = new BigDecimal(Double.toString(d2));
		return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	public static double div(String d1, String d2) {
		return div(d1,d2,DEF_DIV_SCALE);
	}
	public static double div(String d1, String d2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("scale 不能小于0");
		}
		BigDecimal b1 = new BigDecimal(d1);
		BigDecimal b2 = new BigDecimal(d2);
		return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	/**
	 * 相除(默认保留10位小数)
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static double intDiv(Integer d1, Integer d2) {
		return div(d1,d2,DEF_DIV_SCALE);
	}
	public static double intDiv(Integer i1,Integer i2, int scale) {
		if (scale < 0)throw new IllegalArgumentException("scale 不能小于0");
		if(i1==null || i2==null)throw new IllegalArgumentException("参数不能为NULL");
		BigDecimal b1 = new BigDecimal(String.valueOf(i1));
		BigDecimal b2 = new BigDecimal(String.valueOf(i2));
		return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	/**
	 * 格式化小数点位
	 * @param d1 数据
	 * @param len
	 * @return
	 */
	public static double cutScale(Double num,int len) {
		if(num==null)throw new IllegalArgumentException("num != null");
		BigDecimal bd = new BigDecimal(Double.toString(num));
		return bd.setScale(len,BigDecimal.ROUND_DOWN).doubleValue();
	}
	/**
	 * 格式化double金额
	 * @param d 数据值
	 * @param len 长度
	 * @param type 类别 @seee BigDecimal.ROUND_HALF_UP,BigDecimal.ROUND_HALF_DOWN等
	 * @return
	 */
	public final static double setScale(double d, int len, int type) {
		d = d * 100 / 100.00;
		BigDecimal b = new BigDecimal(d);
		return b.setScale(len, type).doubleValue();
	}
	public static void main(String[] args) {
		System.out.println(intDiv(400,666)*100);
	}
}
