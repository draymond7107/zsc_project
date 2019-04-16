package com.zsc.tbpractice.http.dingding;


import com.zsc.base.utils.CryptUtils;
import com.zsc.base.utils.SignUtils;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangSuchao
 * @create 2019/4/14
 * @since 1.0.0
 */

public class DingDingUtils {
    /**
     * 个人免登场景的签名计算方法
     *
     * @param appSecret
     * @return
     * @throws Exception
     */
    public static String getPersonalLoginSign(String appSecret) throws Exception {
        String stringToSign = String.valueOf(System.currentTimeMillis());

        Mac mac = Mac.getInstance("HmacSHA256");       //散列算法
        mac.init(new SecretKeySpec(appSecret.getBytes("UTF-8"), "HmacSHA256"));
        byte[] signatureBytes = mac.doFinal(stringToSign.getBytes("UTF-8"));
        String signature = new String(CryptUtils.base64Encode(signatureBytes));//
        String urlEncodeSignature = SignUtils.urlEncode(signature);//解码（默认使用UTF-8）

        return urlEncodeSignature;
    }

    /**
     * 三方访问接口的签名计算方法
     *
     * @param suiteTicket
     * @param suiteSecret
     * @return
     * @throws Exception
     */
    public static String getThirdPartAppSign(String timestamp, String suiteTicket, String suiteSecret) throws Exception {

        String stringToSign = timestamp + "\n" + suiteTicket;
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(suiteSecret.getBytes("UTF-8"), "HmacSHA256"));
        byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
        String signature = CryptUtils.base64Encode(signData);

        String urlEncodeSignature = SignUtils.urlEncode(signature);//解码（默认使用UTF-8）
        return urlEncodeSignature;
    }

    /**
     * 对头信息赋值,应放到httpUtils方法
     * (简短的头信息赋值)
     *
     * @return
     */
    public static List<Header> getShortHeader() {
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8"));
        headers.add(new BasicHeader("Content-Type", "application/json;charset=UTF-8"));
        headers.add(new BasicHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6,zh-TW;q=0.4"));

        return headers;
    }

    /**
     * 对头信息赋值
     * (全部的头信息赋值)
     * 需要重新整理，代表意义还没全懂
     *
     * @return
     */
    public static List<Header> getAllHeader() {
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8"));
        headers.add(new BasicHeader("Accept-Encoding", "gzip, deflate, sdch"));
        headers.add(new BasicHeader("Connection", "keep-alive"));
        headers.add(new BasicHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6,zh-TW;q=0.4"));
        // 浏览器表示
        headers.add(new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36"));
        //        httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.7.6)");
        //headers.add(new BasicHeader("Host","html.pertech.cn"));
        //headers.add(new BasicHeader("Referer","http://html.pertech.cn/client/course/confirm"));

        return headers;
    }


}
