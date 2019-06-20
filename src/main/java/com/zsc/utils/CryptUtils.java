package com.zsc.utils;

import com.zsc.base.Config;
import org.apache.commons.codec.binary.Base64;
import java.io.UnsupportedEncodingException;

/**
 * @author ZhangSuchao
 * @create 2019/6/20
 * @since 1.0.0
 */

public class CryptUtils {

    /**
     * base64编码
     *
     * @param str
     * @param dec
     * @return
     */
    public static String base64Encode(String str, String dec) {
        try {
            byte[] bytes = str.getBytes(dec);
            return base64Encode(bytes);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * base64编码
     *
     * @param bytes
     * @return
     */
    public static String base64Encode(byte[] bytes) {
        Base64 base64 = new Base64();
        byte[] encode = base64.encode(bytes);
        return new String(encode);
    }

    /**
     * base64编码
     *
     * @return
     */
    public static String base64Encode(String str) {
        Base64 base64 = new Base64();

        byte[] bytes = new byte[0];
        try {
            bytes = str.getBytes(Config.ENC_UTF);
            byte[] encode = base64.encode(bytes);
            return new String(encode);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * base64解码
     *
     * @param str
     * @return
     */
    public static String base64Decode(String str) {
        Base64 base64 = new Base64();
        byte[] decode = base64.decode(str);
        return new String(decode);
    }

    public static String base64Decode(String str, String dec) {
        Base64 base64 = new Base64();
        try {
            byte[] bytes = str.getBytes(dec);

            byte[] decode = base64.decode(bytes);
            return new String(decode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
