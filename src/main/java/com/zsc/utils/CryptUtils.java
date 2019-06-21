package com.zsc.utils;

import com.taobao.api.internal.toplink.embedded.websocket.util.StringUtil;
import com.zsc.base.Config;
import com.zsc.base.utils.StringUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author ZhangSuchao
 * @create 2019/6/20
 * @since 1.0.0
 */

public class CryptUtils {
    //#########################    Base64编码     ######################################################################

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

    //#########################   URL编码    ######################################################################

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
//######################### jdk  SHA/MD5算法   ######################################################################

    /**
     * jdk版本
     * md5加密转成hex返回
     *
     * @return
     */
    public static String md5(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("md5");
            byte[] bytes = str.getBytes();
            messageDigest.update(bytes);
            byte[] digest = messageDigest.digest();
            return Hex.encodeHexString(digest).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * jdk版本
     * SHA1加密
     *
     * @param str
     * @return
     */
    public static String SHA1(String str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA1");
            messageDigest.update(str.getBytes());
            byte[] digest = messageDigest.digest();
            return Hex.encodeHexString(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String SHA256(String message, String mesEnc) {
        return SHA(message, mesEnc, "SHA-256");
    }

    /**
     * @param message 待加密文本
     * @param mesEnc  待加密文本编码格式utf-8/jbk/iso-8891-1
     * @param shaEnc  加密方法/sha1/sha256/sha384/sha512/md5
     * @return
     */
    public static String SHA(String message, String mesEnc, String shaEnc) {
        if (org.apache.commons.lang3.StringUtils.isEmpty(message)) {
            return null;
        }
        try {
            byte[] bytes = message.getBytes(mesEnc);
            MessageDigest messageDigest = MessageDigest.getInstance(shaEnc);
            byte[] digest = messageDigest.digest(bytes);
            return byteArr2Hex(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

//#########################  HMAC加密 签名算法  ######################################################################

    /**
     * 密钥生成签名
     * 接收方根据根据密钥，再一次生成签名，对比签名，校验数据是否被篡改
     *
     * @param type HmacMD5/HmacSHA256/
     * @param data 待加密体
     * @param key  密钥
     * @return
     */
    public static String getSign(String type, String data, String key) {
        String result = null;
        try {
            SecretKeySpec signinKey = new SecretKeySpec(key.getBytes(), type);
            Mac mac = Mac.getInstance(type);
            mac.init(signinKey);
            byte[] rawHmac = mac.doFinal(data.getBytes());
            result = Hex.encodeHexString(rawHmac).toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * byte[]转16进制
     *
     * @param bytes
     * @return
     */
    public static String byteArr2Hex(byte[] bytes) {

        // 首先初始化一个字符数组，用来存放每个16进制字符
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        // new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
        char[] resultCharArray = new char[bytes.length * 2];

        // 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
        int index = 0;
        for (byte b : bytes) {
            resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
            resultCharArray[index++] = hexDigits[b & 0xf];

        }
        // 字符数组组合成字符串返回
        return new String(resultCharArray);


    }
}
