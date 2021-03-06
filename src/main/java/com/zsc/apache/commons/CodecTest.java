package com.zsc.apache.commons;


import com.zsc.base.Config;

/**
 * 编码加密类
 *
 * @author ZhangSuchao
 * @create 2019/6/20
 * @since 1.0.0
 */

public class CodecTest {
    public static void main(String[] args) {
//        base64Encode();
//        base64Incode();
//        base64Incode();
//        test3();
//        urlDecode();
        md5();
//        SHA1();
//        SHA256();
  //      HMAC();
//        genHMAC();

    }

    public static void base64Encode() {
        String str = "中国中";
        String base64Encode0 = com.zsc.utils.CryptUtils.base64Encode(str);
        String base64Encode1 = com.zsc.utils.CryptUtils.base64Encode(str, "ISO-8859-1");
        String base64Encode2 = com.zsc.utils.CryptUtils.base64Encode(str, "UTF-8");
        String base64Encode3 = com.zsc.utils.CryptUtils.base64Encode(str, "8859_1");
        String base64Encode4 = com.zsc.utils.CryptUtils.base64Encode(str, "gbk");
        System.out.println(base64Encode1);

        //     com.zsc.utils.CryptUtils.de

    }

    public static void base64Incode() {
        String str = "中国中";
        String base64Encode2 = com.zsc.utils.CryptUtils.base64Encode(str, "UTF-8");
        String s = com.zsc.utils.CryptUtils.base64Decode(base64Encode2);
        System.out.println(s);

    }

    public static void test3() {
        String str = "中国中";
        String base64Encode2 = com.zsc.utils.CryptUtils.base64Encode(str, "UTF-8");
        String s = com.zsc.utils.CryptUtils.base64Decode(base64Encode2, "UTF-8");
        System.out.println(s);
    }

    public static void urlDecode() {
        String url = "api://www.baidu.com?name=zsc&pass=sss";
        String urlEncode = com.zsc.utils.CryptUtils.urlEncode(url, Config.ENC_UTF);
        System.out.println(urlEncode);
        String urlDecode = com.zsc.utils.CryptUtils.urlDecode(urlEncode);
        System.out.println(111);
    }

    public static void md5() {
        String str = "我们在哪？";
        String s = com.zsc.utils.CryptUtils.md5(str);
        String s1 = com.zsc.utils.CryptUtils.SHA1(str);
        String s2 = com.zsc.utils.CryptUtils.SHA256(str, "UTF-8");
        System.out.println(s);

    }

    public static void SHA1() {
        String str = "我们在哪？";
        String s = com.zsc.utils.CryptUtils.SHA1(str);
        System.out.println(s);
    }

    public static void SHA256() {
        String str = "我们在哪？";
        String s = com.zsc.utils.CryptUtils.SHA256(str, "UTF-8");
        System.out.println(s);

    }


    public static void genHMAC() {

        String str = "我们在哪？";
        String s = com.zsc.utils.CryptUtils.getSign("HmacMD5", str, "fgdfgregergrgr");

        String s1 = com.zsc.utils.CryptUtils.getSign("HmacSHA256", str, "fgdfgregergrgr");
        System.out.println(111);
    }


}
