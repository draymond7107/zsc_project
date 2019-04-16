package com.zsc.tbpractice.http.dingding;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiServiceGetCorpTokenRequest;
import com.dingtalk.api.request.OapiUserGetAdminRequest;
import com.dingtalk.api.response.OapiServiceGetCorpTokenResponse;
import com.dingtalk.api.response.OapiUserGetAdminResponse;
import com.zsc.base.http.HttpResult;
import com.zsc.base.http.HttpUtils;
import com.zsc.base.spring.JsonResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.zsc.tbpractice.mina.concurrent.Main.httpResult;

/**
 * 第三方企业应用
 *
 * @author ZhangSuchao
 * @create 2019/4/15
 * @since 1.0.0
 */
@RestController
@RequestMapping("/dingding/ThirdPartApp")
public class ThirdPartApp {
    private Log logger = LogFactory.getLog(ThirdPartApp.class);

    private String suiteTicket = "suiteTicket";//钉钉推送的suiteTicket。测试应用可以随意填写。
    private String suiteKey = "suitem3ybhsgqlucdfqot";
    private String suiteSecret = "u4VFx_exPIuXR-91jYIiBheta-4jXoAojAnmat2j25SL2UHXGuAgoomNruNAvu9Z";
    //获取企业凭证url
    private String get_corp_token = "https://oapi.dingtalk.com/service/get_corp_token";

    /**
     * 获取企业凭证
     *
     * @param corpId 授权企业corpId,组装为JSON结构置于http post body部分
     * @return
     */
    @RequestMapping("/get_corp_token")
    public String get_corp_token(String corpId) throws Exception {
        //   https://oapi.dingtalk.com/service/get_corp_token?signature=kKlP1QmmXXX&timestamp=1527130370219&suiteTicket=xxx&accessKey=suitezmpdnvsw4xxxxx
        //  请求头
        List<Header> headers = DingDingUtils.getAllHeader();
        //请求体
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("auth_corpid", corpId);
        Object objecrt = JSON.toJSON(jsonObject);//
        // String data = jsonObject.toJSONString(); String类型的json格式报错

        //  timestamp
        String timestamp = String.valueOf(System.currentTimeMillis());
        //  签名
        String signature = DingDingUtils.getThirdPartAppSign(timestamp, suiteTicket, suiteSecret);
        //  url
        String url = get_corp_token + "?signature=" + signature + "&timestamp=" + timestamp + "&suiteTicket=" + suiteTicket + "&accessKey=" + suiteKey;
        JSONObject response = HttpHelper.doPost(url, objecrt);
        //  HttpResult httpResult = HttpUtils.post(url, data, "UTF-8", headers);
        logger.error(response);

        Integer errcode = (Integer) response.get("errcode");
        String errmsg = (String) response.get("errmsg");
        if (0 == errcode && "ok".equals(errmsg)) {
            return (String) response.get("access_token");
        }

        return null;
    }



}
