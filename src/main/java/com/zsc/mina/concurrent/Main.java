package com.zsc.mina.concurrent;

import com.alibaba.fastjson.JSONObject;


import com.zsc.base.http.HttpResult;
import com.zsc.base.utils.CryptUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class Main {
    // public static Log logger = LogFactory.getLog(HttpMain.class);
    public static HttpResult httpResult = null;
    public static String retHtml = null;
    public static String accessKey = "dingoad8tugyde47zjfeid";
    public static String appSecret = "dKOHh4Kqouy7eZfONqXh1FsulUtAJEu_odhyLr9hnUbsn67De_TePTQNHcWE4i2h";
    public static String tmp_auth_code = "d9e6409aea453a1fbbf5bcaa928d7ae0";


    public static void main(String[] args) {
        try {
            //https://oapi.dingtalk.com/connect/oauth2/sns_authorize?appid=APPID&response_type=code&scope=snsapi_auth&state=STATE&redirect_uri=REDIRECT_URI
            //    String url = "https://oapi.dingtalk.com/sns/getuserinfo_bycode?signature=${signature}&timestamp=${timestamp}&accessKey=${accessKey}";
            String url = "https://oapi.dingtalk.com/sns/getuserinfo_bycode?signature=${signature}&timestamp=${timestamp}&accessKey=${accessKey}";
            String timestamp = String.valueOf(System.currentTimeMillis());

            //

            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(appSecret.getBytes("UTF-8"), "HmacSHA256"));
            byte[] signatureBytes = mac.doFinal(timestamp.getBytes("UTF-8"));
            String signature = CryptUtils.base64Encode(signatureBytes);
            String urlEncodeSignature = CryptUtils.urlEncode(signature);
            url = url.replace("${timestamp}", timestamp);
            url = url.replace("${accessKey}", accessKey);
            url = url.replace("${signature}", urlEncodeSignature);

            JSONObject data = new JSONObject();
            data.put("tmp_auth_code", tmp_auth_code);
            data.put("signature", urlEncodeSignature);
            data.put("accessKey", accessKey);
            data.put("timestamp", timestamp);
            url = "https://oapi.dingtalk.com/sns/getuserinfo_bycode";

//            DefaultDingTalkClient  client = new DefaultDingTalkClient("https://oapi.dingtalk.com/sns/getuserinfo_bycode");
//            OapiSnsGetuserinfoBycodeRequest req = new OapiSnsGetuserinfoBycodeRequest();
//            req.setTmpAuthCode("4a2c5695b78738d495f47b5fee9160cd");
//            OapiSnsGetuserinfoBycodeResponse response = client.execute(req,"yourAppId","yourAppSecret");




        } catch (Exception e) {
            e.printStackTrace();
        }
        retHtml = httpResult.makeHtml("utf-8");
        //   logger.error("[1]请求返回:" + retHtml);
    }
}
