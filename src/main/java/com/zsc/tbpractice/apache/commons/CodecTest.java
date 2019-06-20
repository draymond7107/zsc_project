package com.zsc.tbpractice.apache.commons;


import com.zsc.base.Config;
import com.zsc.base.utils.CryptUtils;
import org.apache.commons.codec.binary.Base64;

/**
 * 编码加密类
 *
 * @author ZhangSuchao
 * @create 2019/6/20
 * @since 1.0.0
 */

public class CodecTest {
    public static void main(String[] args) {
        //     base64Encode();
        //      test2();
        // base64Incode();
        //test3();
        test4();
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

    public static void test4() {
        String url = "http://www.baidu.com?name=zsc&pass=sss";
        String urlEncode = com.zsc.utils.CryptUtils.urlEncode(url, Config.ENC_UTF);
        System.out.println(urlEncode);
        String urlDecode = com.zsc.utils.CryptUtils.urlDecode(urlEncode);
        System.out.println(111);
    }

    public static void test5() {
    }

    public static void test6() {
    }

    public static void test7() {
    }

    public static void test8() {
    }

    public static void test9() {
    }

    public static void test10() {
    }

}
