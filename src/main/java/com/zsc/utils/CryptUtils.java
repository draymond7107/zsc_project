package com.zsc.utils;

import com.zsc.base.Config;
import com.zsc.base.utils.StringUtils;
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

    public static String urlEncode(String url) {
        return urlEncode(url, Config.ENC_UTF);
    }

    /**
     * url加密
     *
     * @param url
     * @param enc
     * @return
     */
    public static String urlEncode(String url, String enc) {
        if (StringUtils.isEmpty(url)) return url;
        try {

            return java.net.URLEncoder.encode(url, enc);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * url解码
     *
     * @param url
     * @return
     */
    public static String urlDecode(String url) {
        return urlDecode(url, Config.ENC_UTF);
    }

    public static String urlDecode(String url, String enc) {
        if (StringUtils.isEmpty(url)) return url;
        try {
            return java.net.URLDecoder.decode(url, enc);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
