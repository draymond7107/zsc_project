package com.zsc.tbpractice.http.dingding;


import com.alibaba.fastjson.JSONObject;
import com.zsc.base.http.HttpResult;
import com.zsc.base.http.HttpUtils;
import com.zsc.base.spring.JsonResult;
import com.zsc.base.utils.CryptUtils;
import com.zsc.base.utils.SignUtils;
import com.zsc.general.entity.User;
import com.zsc.tbpractice.TbpracticeApplication;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.annotation.Contract;
import org.apache.http.message.BasicHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangSuchao
 * @create 2019/4/14
 * @since 1.0.0
 */
@RestController
@RequestMapping("/DingDing")
public class DingDing {
    static Log logger = LogFactory.getLog(TbpracticeApplication.class);

    //AccessToken是企业自建应用或者被企业授权开通的第三方应用访问钉钉服务端开放接口的全局唯一凭证。

    @RequestMapping("/getAccessToken")
    public String getAccessToken() throws Exception {
        logger.info("获取钉钉AccessToken");

        String accessKey = "dingoajajoyv0fgdvyf05g";
        String appSecret = "lYhCPZ8WuGJDQyvcWJdN1VH2qom_M-srDnaH2moKVUft2XIz89Slk4HkYdL6Iuob";
        // URL
        String url = "https://oapi.dingtalk.com/gettoken";
        // 请求头
        List<Header> headers = DingDingUtils.getShortHeader();

        url = url + "?appkey=" + accessKey + "&appsecret=" + appSecret;
        HttpResult httpResult = HttpUtils.get(url, headers);

        String returnHtml = httpResult.getHtml();
        logger.info("<httpResult> 返回的结果：" + returnHtml);

        if (httpResult.isSuccess()) {
            //TODO 依据返回的格式在做处理

        }

        return null;
    }

    /**
     * 获取钉钉的用户详情
     *
     * @param userId 员工在当前企业内的唯一标识，也称staffId。可由企业在创建时指定，并代表一定含义比如工号，创建后不可修改
     * @return
     * @throws Exception
     */
    @RequestMapping("/getUserInfoByUserId")
    public JsonResult getUserInfoByUserId(String userId) throws Exception {
        //获取AccessToken
        String accessToken = getAccessToken();
        // URL
        String url = "https://oapi.dingtalk.com/user/get";
        // 请求头
        List<Header> headers = DingDingUtils.getShortHeader();

        url = url + "?access_token=" + accessToken + "&userid=" + userId;
        HttpResult httpResult = HttpUtils.get(url, headers);
        String returnHtml = httpResult.getHtml();
        logger.info("<httpResult> 返回的结果：" + returnHtml);

        if (httpResult.isSuccess()) {
            //TODO 依据返回的格式在做处理

        }

        return null;
    }

    /**
     * 获取部门用户userid列表
     *
     * @param deptId
     * @return
     */
    @RequestMapping("/getDeptMemberIdListByDeptId")
    public JsonResult getDeptMemberIdListByDeptId(String deptId) {
        try {
            String accessToken = getAccessToken();
            List<Header> shortHeader = DingDingUtils.getShortHeader();

            String url = "https://oapi.dingtalk.com/user/getDeptMember";

            url = url + "?access_token=" + accessToken + "&deptId=" + deptId;

            HttpResult httpResult = HttpUtils.get(url, shortHeader);
            String returnHtml = httpResult.getHtml();
            logger.info("<httpResult> 返回的结果：" + returnHtml);

            if (httpResult.isSuccess()) {
                //TODO 依据返回的格式在做处理

            }

        } catch (Exception e) {
            logger.error("获取部门用户userid列表:getDeptMemberIdListByDeptId" + e.getMessage());
        }
        return null;
    }

    /**
     * 创建用户
     *
     * @param user
     * @return
     */
    public JsonResult addUser(DingUser user) {
        try {
            String accessToken = getAccessToken();
            String url = "https://oapi.dingtalk.com/user/create";
            //请求头
            List<Header> headers = DingDingUtils.getAllHeader();
            //请求体
            String data = JSONObject.toJSONString(user);

            url = url + "?access_token=" + accessToken;
            HttpResult httpResult = HttpUtils.post(url, data, "UTF-8", headers);
            String returnHtml = httpResult.getHtml();
            logger.info("<httpResult> 返回的结果：" + returnHtml);

            if (httpResult.isSuccess()) {
                //TODO 依据返回的格式在做处理

            }

        } catch (Exception e) {
            logger.error("创建用户失败：addUser" + e.getMessage());
        }
        return null;
    }

}
