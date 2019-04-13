package com.zsc.base.utils;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

public class ByteBufferUtils {
	private static String hexString="0123456789ABCDEF";  
	/**
	 * 字节转换无符号数字
	 * @param b
	 * @return
	 */
	public static int unsingedByte(byte b) {
		return b & 0xff;
	}

	/**
	 * 整形转无符号数字
	 * @param intNum
	 * @return
	 */
	public static long unsingedInt(int intNum) {
		return (long) (intNum & 0x0FFFFFFFFl);
	}
	/**字节转Int**/
	public static int toInt(byte[] bytes) {
		return toInt(bytes,0);
	}
	/**字节指定位置转int**/
	public static int toInt(byte[] bytes, int offset) {
		int mask = 0xff;
		long temp = 0;
		int res = 0;
		for (int i = offset; i < offset + 4; i++) {
			res <<= 8;
			temp = bytes[i] & mask;
			res |= temp;
		}
		return res;
	}
	/**字节转Long**/
	public static long toLong(byte[] bytes) {
		return toLong(bytes, 0);
	}
	/**字节指定位置转Long**/
	public static long toLong(byte[] bytes, int offset) {
		int mask = 0xff;
		long temp = 0;
		long res = 0;
		for (int i = offset; i < offset + 8; i++) {
			res <<= 8;
			temp = bytes[i] & mask;
			res |= temp;
		}
		return res;
	}
	/**字节转Short**/
	public static short toShort(byte[] bytes) {
		return toShort(bytes,0);
	}
	/**字节指定位置转Short**/
	public static short toShort(byte[] bytes, int offset) {
		int mask = 0xff;
		long temp = 0;
		short res = 0;
		for (int i = offset; i < offset + 2; i++) {
			res <<= 8;
			temp = bytes[i] & mask;
			res |= temp;
		}
		return res;
	}
	/**int转字节**/
	public static byte[] toBytes(int num) {
		byte[] b = new byte[4];
		for (int i = b.length - 1; i > -1; i--) {
			b[i] = (byte)num;
			num >>>= 8;
		}
		return b;
	}
	/**短整型转字节**/
	public static byte[] toBytes(short num) {
		byte[] b = new byte[2];
		for (int i = b.length - 1; i > -1; i--) {
			b[i] = (byte)num;
			num >>>= 8;
		}
		return b;
	}
	/**长整型转字节**/
	public static byte[] toBytes(long num) {
		byte[] b = new byte[8];
		for (int i = b.length - 1; i > -1; i--) {
			b[i] = (byte)num;
			num >>>= 8;
		}
		return b;
	}
	/**高位字符串转字节**/
	public static byte[] toBytes(String hex, String split) {
		if (hex == null) {
			return null;
		}
		String[] hexArray;
		if (split == null) {
			if ((hex.length() % 2) != 0) {
				hex = "0" + hex;
			}
			hexArray = new String[hex.length() / 2];
			int index = 0;
			for (int i = 0; i < hexArray.length; i += 2) {
				hexArray[index++] = hex.substring(i, i + 2);
			}
		} else {
			hexArray = hex.split(split);
			for (int i = 0; i < hexArray.length; i++) {
				if (hexArray[i].length() < 2) {
					hexArray[i] = "0" + hexArray[i];
				}
				if (hexArray[i].length() > 2) {
					hexArray[i] = hexArray[i].substring(0, 2);
				}
			}
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream(hexArray.length);
		for (int i = 0; i < hexArray.length; i++) {
			String item = hexArray[i];
			try {
				out.write((byte) Integer.parseInt(item, 16));
			} catch (Exception e) {
				return null;
			}
		}
		return out.toByteArray();
	}
	/**转高位字符串**/
	public static String toHexValue(ByteBuffer buffer) {
		StringBuffer result = new StringBuffer(buffer.remaining() * 2);
		while (buffer.hasRemaining()) {
			String value = Integer.toHexString(buffer.get() & 0xff);
			if (value.length() == 1)
				result.append("0"); // ensure 2 digit
			result.append(value);
		}
		return result.toString();
	}
	/**长整型转高位字符串**/
	public static String toHexValue(long l) {
		ByteBuffer buffer = ByteBuffer.allocate(8);
		buffer.putLong(0, l);
		return toHexValue(buffer);
	}

	
	/* 将bytes数组转化为16进制字符串 */
	public static String bytesToHexString(byte[] src){
		StringBuilder stringBuilder = new StringBuilder("");
		if(src == null || src.length <= 0){
			return null;
		}
		for(int i = 0;i < src.length;i++){
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if(hv.length() < 2){
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString().toUpperCase();
	}

	/* 将16进制字符串转化为bytes数组 */
	public static byte[] hexStringToByte(String hex){
		int len = (hex.length() / 2);
		byte[] result = new byte[len];
		char[] achar = hex.toCharArray();
		for(int i = 0;i < len;i++){
			int pos = i * 2;
			result[i] = (byte)(toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
		}
		return result;
	}
	
	/*将Short Int类型转化为16进制再转化为byte数组*/  
    private static byte toByte(char c) {     
        byte b = (byte)hexString.indexOf(c);     
        return b;     
    }  

	public static void main(String[] args) {
		System.out.println(toHexValue(1));
	}
}
