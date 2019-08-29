package com.zsc.api.dingding;


import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.taobao.api.ApiException;
import com.taobao.api.internal.toplink.embedded.websocket.util.StringUtil;
import com.zsc.base.http.HttpResult;
import com.zsc.base.http.HttpUtils;
import com.zsc.base.spring.JsonResult;
import com.zsc.base.utils.CryptUtils;
import com.zsc.base.utils.SignUtils;
import com.zsc.base.utils.StringUtils;
import com.zsc.tbpractice.general.entity.User;
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
import java.util.Map;

/**
 * @author ZhangSuchao
 * @create 2019/4/14
 * @since 1.0.0
 */
@RestController
@RequestMapping("/DingDing/TongXunLu")
public class TongXunLu {
    static Log logger = LogFactory.getLog(TbpracticeApplication.class);

    //AccessToken是企业自建应用或者被企业授权开通的第三方应用访问钉钉服务端开放接口的全局唯一凭证。
    public String getAccessToken() throws Exception {
        logger.info("获取钉钉AccessToken");
        String accessKey = "dingrinbe0lsbhkidqyy";
        String appSecret = "ap8VlF8He0TL9GhHrGQ2aNKGWJVz4L4QCxsN5zraHifQ76hTxORj027RJSjsx-PT";
        // URL
        String url = "https://oapi.dingtalk.com/gettoken";
        // 请求头
        //    List<Header> headers = DingDingUtils.getShortHeader();
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8"));
        headers.add(new BasicHeader("Content-Type", "application/json;charset=UTF-8"));
        headers.add(new BasicHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6,zh-TW;q=0.4"));
        url = url + "?appkey=" + accessKey + "&appsecret=" + appSecret;

        HttpResult httpResult = HttpUtils.get(url, headers);

        String returnHtml = httpResult.getHtml();
        logger.info("<httpResult> 返回的结果：" + returnHtml);
        if (httpResult.isSuccess()) {
            //TODO 依据返回的格式在做处理
            JSONObject jsonObject = httpResult.asJSON();
            Object o = jsonObject.get("access_token");
            String s = o.toString();
            return s;
        }

        return null;
    }

    /**
     * 根据accessToken与code获得用户id
     *
     * @param accessToken
     * @param code
     * @return
     * @throws Exception
     */
    public String getUserIdByAccessTokenAndCode(String accessToken, String code) throws Exception {


        String url = "https://oapi.dingtalk.com/user/getuserinfo" + "?access_token=" + accessToken + "&code=" + code;
        List<Header> headers = DingDingUtils.getShortHeader();
        HttpResult httpResult = HttpUtils.get(url, headers);
        String html = httpResult.getHtml();
        logger.error(html);
        //FIXME
        return "1";
    }

    /**
     * 获取用户详情
     *
     * @param accessToken
     * @param userid
     * @return
     */
    //FIXME 封装到一个对象里面
    public Map<String, Object> getUserByUserid(String accessToken, String userid) throws Exception {
        //头信息
        List<Header> shortHeader = DingDingUtils.getShortHeader();
        //Url
        String url = "https://oapi.dingtalk.com/user/get?access_token=" + accessToken + "&userid=" + userid;
        HttpResult httpResult = HttpUtils.get(url, shortHeader);
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
     * @param accessToken
     * @param deptId
     * @return
     * @throws Exception
     */
    @RequestMapping("/getDeptMemberUserIdListByDeptId")
    public JsonResult getDeptMemberUserIdListByDeptId(String accessToken, String deptId) throws Exception {
        //头信息
        List<Header> shortHeader = DingDingUtils.getShortHeader();
        //Url
        String url = "https://oapi.dingtalk.com/user/getDeptMember?access_token=" + accessToken + "&deptId=" + deptId;
        HttpResult httpResult = HttpUtils.get(url, shortHeader);
        String returnHtml = httpResult.getHtml();
        logger.info("<httpResult> 返回的结果：" + returnHtml);
        if (httpResult.isSuccess()) {


            //TODO 依据返回的格式在做处理
        }
        return null;


    }

    /**
     * 根据部门id获取部门用户列表
     * 待测试：
     *
     * @param accessToken
     * @param departmentId
     * @return
     * @throws Exception
     */
    @RequestMapping("/getUserListBydepartmentid")
    public JsonResult getUserListBydepartmentid(String accessToken, String departmentId, Long offset, Integer size, String order) throws Exception {
        if (offset == null || offset == 0L) {
            offset = 10L;
        }
        if (size == null || size == 0) {
            size = 1;
        }

        //头信息
        List<Header> shortHeader = DingDingUtils.getShortHeader();
        //url
        String url = "https://oapi.dingtalk.com/user/simplelist?access_token=" + accessToken + "&department_id=" + departmentId + "&offset=" + offset + "&size=" + size;
        if (StringUtils.isNotEmpty(order) && ("entry_asc".equals(order) || "entry_desc".equals(order) || "modify_asc".equals(order) || "modify_desc".equals(order) || "custom".equals(order))) {
            url = url + "&order" + order;
        }
        HttpResult httpResult = HttpUtils.get(url, shortHeader);
        String returnHtml = httpResult.getHtml();
        logger.info("<httpResult> 返回的结果：" + returnHtml);
        if (httpResult.isSuccess()) {

            //TODO 依据返回的格式在做处理
        }
        return null;


    }


    /**
     * 根据部门id获取部门用户详情列表（用户的部门列表也有返回）
     *
     * @param accessToken
     * @param departmentId
     * @return
     * @throws Exception
     */
    @RequestMapping("/getDepartmentInfoBydepartmentid")
    public JsonResult getDepartmentInfoBydepartmentid(String accessToken, String departmentId, Long offset, Integer size, String order) throws Exception {
        if (offset == null || offset == 0L) {
            offset = 10L;
        }
        if (size == null || size == 0) {
            size = 1;
        }

        //头信息
        List<Header> shortHeader = DingDingUtils.getShortHeader();
        //url
        String url = "https://oapi.dingtalk.com/user/listbypage?access_token=" + accessToken + "&department_id=" + departmentId + "&offset=" + offset + "&size=" + size;
        if (StringUtils.isNotEmpty(order) && ("entry_asc".equals(order) || "entry_desc".equals(order) || "modify_asc".equals(order) || "modify_desc".equals(order) || "custom".equals(order))) {
            url = url + "&order" + order;
        }
        HttpResult httpResult = HttpUtils.get(url, shortHeader);
        String returnHtml = httpResult.getHtml();
        logger.info("<httpResult> 返回的结果：" + returnHtml);
        if (httpResult.isSuccess()) {

            //TODO 依据返回的格式在做处理
        }
        return null;


    }

    /**
     * 获取管理员列表
     *
     * @param accessToken
     * @return
     * @throws Exception
     */
    @RequestMapping("/getAdminList")
    public JsonResult getAdminList(String accessToken) throws Exception {
        //头信息
        List<Header> shortHeader = DingDingUtils.getShortHeader();
        //Url
        String url = "https://oapi.dingtalk.com/user/get_admin?access_token=" + accessToken;
        HttpResult httpResult = HttpUtils.get(url, shortHeader);
        String returnHtml = httpResult.getHtml();
        logger.info("<httpResult> 返回的结果：" + returnHtml);
        if (httpResult.isSuccess()) {


            //TODO 依据返回的格式在做处理
        }
        return null;

    }

    /**
     * 获取管理员通讯录权限范围
     *
     * @param accessToken
     * @return
     * @throws Exception
     */
    @RequestMapping("/getAdminScopeByUserId")
    public JsonResult getAdminScopeByUserId(String accessToken, String userId) throws Exception {
        //头信息
        List<Header> shortHeader = DingDingUtils.getShortHeader();
        //Url
        String url = "https://oapi.dingtalk.com/topapi/user/get_admin_scope?access_token=" + accessToken + "&userId=" + userId;
        HttpResult httpResult = HttpUtils.get(url, shortHeader);
        String returnHtml = httpResult.getHtml();
        logger.info("<httpResult> 返回的结果：" + returnHtml);
        if (httpResult.isSuccess()) {


            //TODO 依据返回的格式在做处理
        }
        return null;

    }


    /**
     * 查询管理员是否具备管理某个应用的权限
     * 应用必须是ISV所开发，且管理员所在组织开通了此应用。
     *
     * @param accessToken
     * @return
     * @throws Exception
     */
    @RequestMapping("/can_access_microapp")
    public JsonResult can_access_microapp(String accessToken, String appId, String userId) throws Exception {
        //头信息
        List<Header> shortHeader = DingDingUtils.getShortHeader();
        //Url
        String url = "https://oapi.dingtalk.com/user/can_access_microapp?access_token=" + accessToken + "&appId" + appId + "&userId=" + userId;
        HttpResult httpResult = HttpUtils.get(url, shortHeader);
        String returnHtml = httpResult.getHtml();
        logger.info("<httpResult> 返回的结果：" + returnHtml);
        if (httpResult.isSuccess()) {


            //TODO 依据返回的格式在做处理
        }
        return null;

    }

    /**
     * 根据unionid获取userid
     *
     * @param accessToken
     * @param unionid     员工在当前开发者企业账号范围内的唯一标识，系统生成，固定值，不会改变
     * @return
     * @throws Exception
     */
    @RequestMapping("/getUserIdByUnionId")
    public JsonResult getUserIdByUnionId(String accessToken, String unionid) throws Exception {
        //头信息
        List<Header> shortHeader = DingDingUtils.getShortHeader();
        //Url
        String url = "https://oapi.dingtalk.com/user/getUseridByUnionid?access_token=" + accessToken + "&unionid=" + unionid;
        HttpResult httpResult = HttpUtils.get(url, shortHeader);
        String returnHtml = httpResult.getHtml();
        logger.info("<httpResult> 返回的结果：" + returnHtml);
        if (httpResult.isSuccess()) {


            //TODO 依据返回的格式在做处理
        }
        return null;

    }


    /**
     * 获取子部门ID列表
     *
     * @param id 父部门id。根部门的话传1
     * @return
     */
    @RequestMapping("/getDepartmentIdListByParentId")
    public JsonResult getDepartmentIdListByParentId(String accessToken, String id) throws Exception {

        if (StringUtils.isEmpty(id)) {
            id = "1";
        }
        //头信息
        List<Header> shortHeader = DingDingUtils.getShortHeader();
        //Url
        String url = "https://oapi.dingtalk.com/department/list_ids?access_token=" + accessToken + "&id=" + id;
        HttpResult httpResult = HttpUtils.get(url, shortHeader);
        String returnHtml = httpResult.getHtml();
        logger.info("<httpResult> 返回的结果：" + returnHtml);
        if (httpResult.isSuccess()) {


            //TODO 依据返回的格式在做处理
        }
        return null;
    }


    public JsonResult getDepartmentList(String accessToken, String id) throws Exception {


        if (StringUtils.isEmpty(id)) {
            id = "1";
        }
        //头信息
        List<Header> shortHeader = DingDingUtils.getShortHeader();
        //Url
        String url = "https://oapi.dingtalk.com/department/list?access_token=" + accessToken + "&id=" + id;
        HttpResult httpResult = HttpUtils.get(url, shortHeader);
        String returnHtml = httpResult.getHtml();
        logger.info("<httpResult> 返回的结果：" + returnHtml);
        //{"errmsg":"ok","department":[{"id":-7,"createDeptGroup":false,"name":"家校通讯录","parentid":1,"autoAddUser":false},
        // {"id":111415610,"createDeptGroup":true,"name":"子部门11用户1","parentid":111686635,"autoAddUser":true},
        // {"tags":"class","id":111457582,"createDeptGroup":false,"name":"四年级1班","parentid":111684685,"autoAddUser":false},
        // {"tags":"class","id":111457583,"createDeptGroup":false,"name":"四年级2班","parentid":111684685,"autoAddUser":false},
        // {"tags":"class","id":111457584,"createDeptGroup":false,"name":"四年级3班","parentid":111684685,"autoAddUser":false},
        // {"tags":"class","id":111457585,"createDeptGroup":false,"name":"四年级4班","parentid":111684685,"autoAddUser":false},
        // {"tags":"class","id":111457586,"createDeptGroup":false,"name":"四年级5班","parentid":111684685,"autoAddUser":false},
        // {"tags":"class","id":111476698,"createDeptGroup":false,"name":"三年级1班","parentid":111684684,"autoAddUser":false},
        // {"tags":"class","id":111476699,"createDeptGroup":false,"name":"三年级2班","parentid":111684684,"autoAddUser":false},
        // {"tags":"class","id":111476700,"createDeptGroup":false,"name":"三年级3班","parentid":111684684,"autoAddUser":false},
        // {"tags":"class","id":111476701,"createDeptGroup":false,"name":"三年级4班","parentid":111684684,"autoAddUser":false},
        // {"tags":"class","id":111476702,"createDeptGroup":false,"name":"三年级5班","parentid":111684684,"autoAddUser":false},
        // {"tags":"class","id":111481564,"createDeptGroup":false,"name":"二年级1班","parentid":111684683,"autoAddUser":false},
        // {"tags":"class","id":111481565,"createDeptGroup":false,"name":"二年级2班","parentid":111684683,"autoAddUser":false},
        // {"tags":"class","id":111481566,"createDeptGroup":false,"name":"二年级3班","parentid":111684683,"autoAddUser":false},
        // {"tags":"class","id":111481567,"createDeptGroup":false,"name":"二年级4班","parentid":111684683,"autoAddUser":false},
        // {"tags":"class","id":111481568,"createDeptGroup":false,"name":"二年级5班","parentid":111684683,"autoAddUser":false},
        // {"id":111483582,"createDeptGroup":false,"name":"老师","parentid":111457582,"autoAddUser":false},
        // {"id":111483583,"createDeptGroup":false,"name":"学生","parentid":111457582,"autoAddUser":false},
        // {"id":111483584,"createDeptGroup":false,"name":"家长","parentid":111457582,"autoAddUser":false},
        // {"id":111483585,"createDeptGroup":false,"name":"老师","parentid":111457583,"autoAddUser":false},
        // {"id":111483586,"createDeptGroup":false,"name":"学生","parentid":111457583,"autoAddUser":false},{"id":111483587,"createDeptGroup":false,"name":"家长","parentid":111457583,"autoAddUser":false},{"id":111483588,"createDeptGroup":false,"name":"老师","parentid":111457584,"autoAddUser":false},{"id":111483589,"createDeptGroup":false,"name":"学生","parentid":111457584,"autoAddUser":false},{"id":111483590,"createDeptGroup":false,"name":"家长","parentid":111457584,"autoAddUser":false},{"id":111483591,"createDeptGroup":false,"name":"老师","parentid":111457585,"autoAddUser":false},{"id":111483592,"createDeptGroup":false,"name":"学生","parentid":111457585,"autoAddUser":false},{"id":111483593,"createDeptGroup":false,"name":"家长","parentid":111457585,"autoAddUser":false},{"id":111483594,"createDeptGroup":false,"name":"老师","parentid":111457586,"autoAddUser":false},{"id":111483595,"createDeptGroup":false,"name":"学生","parentid":111457586,"autoAddUser":false},{"id":111483596,"createDeptGroup":false,"name":"家长","parentid":111457586,"autoAddUser":false},{"tags":"class","id":111528611,"createDeptGroup":true,"name":"一年级1班","parentid":111684682,"autoAddUser":true},{"tags":"class","id":111528612,"createDeptGroup":true,"name":"一年级2班","parentid":111684682,"autoAddUser":true},{"tags":"class","id":111528613,"createDeptGroup":false,"name":"一年级3班","parentid":111684682,"autoAddUser":false},{"tags":"class","id":111528614,"createDeptGroup":false,"name":"一年级4班","parentid":111684682,"autoAddUser":false},{"tags":"class","id":111528615,"createDeptGroup":false,"name":"一年级5班","parentid":111684682,"autoAddUser":false},{"tags":"class","id":111535630,"createDeptGroup":false,"name":"五年级1班","parentid":111684686,"autoAddUser":false},{"tags":"class","id":111535631,"createDeptGroup":false,"name":"五年级2班","parentid":111684686,"autoAddUser":false},{"tags":"class","id":111535632,"createDeptGroup":false,"name":"五年级3班","parentid":111684686,"autoAddUser":false},{"tags":"class","id":111535633,"createDeptGroup":false,"name":"五年级4班","parentid":111684686,"autoAddUser":false},{"tags":"class","id":111535634,"createDeptGroup":false,"name":"五年级5班","parentid":111684686,"autoAddUser":false},{"tags":"class","id":111554597,"createDeptGroup":false,"name":"六年级1班","parentid":111684687,"autoAddUser":false},{"tags":"class","id":111554598,"createDeptGroup":false,"name":"六年级2班","parentid":111684687,"autoAddUser":false},{"tags":"class","id":111554599,"createDeptGroup":false,"name":"六年级3班","parentid":111684687,"autoAddUser":false},{"tags":"class","id":111554600,"createDeptGroup":false,"name":"六年级4班","parentid":111684687,"autoAddUser":false},{"tags":"class","id":111554601,"createDeptGroup":false,"name":"六年级5班","parentid":111684687,"autoAddUser":false},{"id":111573624,"createDeptGroup":false,"name":"老师","parentid":111476698,"autoAddUser":false},{"id":111573625,"createDeptGroup":false,"name":"学生","parentid":111476698,"autoAddUser":false},{"id":111573626,"createDeptGroup":false,"name":"家长","parentid":111476698,"autoAddUser":false},{"id":111573627,"createDeptGroup":false,"name":"老师","parentid":111476699,"autoAddUser":false},{"id":111573628,"createDeptGroup":false,"name":"学生","parentid":111476699,"autoAddUser":false},{"id":111573629,"createDeptGroup":false,"name":"家长","parentid":111476699,"autoAddUser":false},{"id":111573630,"createDeptGroup":false,"name":"老师","parentid":111476700,"autoAddUser":false},{"id":111573631,"createDeptGroup":false,"name":"学生","parentid":111476700,"autoAddUser":false},{"id":111573632,"createDeptGroup":false,"name":"家长","parentid":111476700,"autoAddUser":false},{"id":111573633,"createDeptGroup":false,"name":"老师","parentid":111476701,"autoAddUser":false},{"id":111573634,"createDeptGroup":false,"name":"学生","parentid":111476701,"autoAddUser":false},{"id":111573635,"createDeptGroup":false,"name":"家长","parentid":111476701,"autoAddUser":false},{"id":111573636,"createDeptGroup":false,"name":"老师","parentid":111476702,"autoAddUser":false},{"id":111573637,"createDeptGroup":false,"name":"学生","parentid":111476702,"autoAddUser":false},{"id":111573638,"createDeptGroup":false,"name":"家长","parentid":111476702,"autoAddUser":false},{"tags":"period","id":111579669,"createDeptGroup":false,"name":"小学","parentid":111686554,"autoAddUser":false},{"id":111611630,"createDeptGroup":true,"name":"子部门1","parentid":1,"autoAddUser":true},{"id":111615520,"createDeptGroup":true,"name":"子部门2","parentid":1,"autoAddUser":true},{"id":111639542,"createDeptGroup":false,"name":"老师","parentid":111481564,"autoAddUser":false},{"id":111639543,"createDeptGroup":false,"name":"学生","parentid":111481564,"autoAddUser":false},{"id":111639544,"createDeptGroup":false,"name":"家长","parentid":111481564,"autoAddUser":false},{"id":111639545,"createDeptGroup":false,"name":"老师","parentid":111481565,"autoAddUser":false},{"id":111639546,"createDeptGroup":false,"name":"学生","parentid":111481565,"autoAddUser":false},{"id":111639547,"createDeptGroup":false,"name":"家长","parentid":111481565,"autoAddUser":false},{"id":111639548,"createDeptGroup":false,"name":"老师","parentid":111481566,"autoAddUser":false},{"id":111639549,"createDeptGroup":false,"name":"学生","parentid":111481566,"autoAddUser":false},{"id":111639550,"createDeptGroup":false,"name":"家长","parentid":111481566,"autoAddUser":false},{"id":111639551,"createDeptGroup":false,"name":"老师","parentid":111481567,"autoAddUser":false},{"id":111639552,"createDeptGroup":false,"name":"学生","parentid":111481567,"autoAddUser":false},{"id":111639553,"createDeptGroup":false,"name":"家长","parentid":111481567,"autoAddUser":false},{"id":111639554,"createDeptGroup":false,"name":"老师","parentid":111481568,"autoAddUser":false},{"id":111639555,"createDeptGroup":false,"name":"学生","parentid":111481568,"autoAddUser":false},{"id":111639556,"createDeptGroup":false,"name":"家长","parentid":111481568,"autoAddUser":false},{"id":111645499,"createDeptGroup":false,"name":"老师","parentid":111535630,"autoAddUser":false},{"id":111645500,"createDeptGroup":false,"name":"学生","parentid":111535630,"autoAddUser":false},{"id":111645501,"createDeptGroup":false,"name":"家长","parentid":111535630,"autoAddUser":false},{"id":111645502,"createDeptGroup":false,"name":"老师","parentid":111535631,"autoAddUser":false},{"id":111645503,"createDeptGroup":false,"name":"学生","parentid":111535631,"autoAddUser":false},{"id":111645504,"createDeptGroup":false,"name":"家长","parentid":111535631,"autoAddUser":false},{"id":111645505,"createDeptGroup":false,"name":"老师","parentid":111535632,"autoAddUser":false},{"id":111645506,"createDeptGroup":false,"name":"学生","parentid":111535632,"autoAddUser":false},{"id":111645507,"createDeptGroup":false,"name":"家长","parentid":111535632,"autoAddUser":false},{"id":111645508,"createDeptGroup":false,"name":"老师","parentid":111535633,"autoAddUser":false},{"id":111645509,"createDeptGroup":false,"name":"学生","parentid":111535633,"autoAddUser":false},{"id":111645510,"createDeptGroup":false,"name":"家长","parentid":111535633,"autoAddUser":false},{"id":111645511,"createDeptGroup":false,"name":"老师","parentid":111535634,"autoAddUser":false},{"id":111645512,"createDeptGroup":false,"name":"学生","parentid":111535634,"autoAddUser":false},{"id":111645513,"createDeptGroup":false,"name":"家长","parentid":111535634,"autoAddUser":false},{"id":111656660,"createDeptGroup":false,"name":"老师","parentid":111528611,"autoAddUser":false},{"id":111656661,"createDeptGroup":false,"name":"学生","parentid":111528611,"autoAddUser":false},{"id":111656662,"createDeptGroup":false,"name":"家长","parentid":111528611,"autoAddUser":false},{"id":111656663,"createDeptGroup":false,"name":"老师","parentid":111528612,"autoAddUser":false},{"id":111656664,"createDeptGroup":false,"name":"学生","parentid":111528612,"autoAddUser":false},{"id":111656665,"createDeptGroup":false,"name":"家长","parentid":111528612,"autoAddUser":false},{"id":111656666,"createDeptGroup":false,"name":"老师","parentid":111528613,"autoAddUser":false},{"id":111656667,"createDeptGroup":false,"name":"学生","parentid":111528613,"autoAddUser":false},{"id":111656668,"createDeptGroup":false,"name":"家长","parentid":111528613,"autoAddUser":false},{"id":111656669,"createDeptGroup":false,"name":"老师","parentid":111528614,"autoAddUser":false},{"id":111656670,"createDeptGroup":false,"name":"学生","parentid":111528614,"autoAddUser":false},{"id":111656671,"createDeptGroup":false,"name":"家长","parentid":111528614,"autoAddUser":false},{"id":111656672,"createDeptGroup":false,"name":"老师","parentid":111528615,"autoAddUser":false},{"id":111656673,"createDeptGroup":false,"name":"学生","parentid":111528615,"autoAddUser":false},{"id":111656674,"createDeptGroup":false,"name":"家长","parentid":111528615,"autoAddUser":false},{"tags":"grade","id":111684682,"createDeptGroup":false,"name":"一年级2018级","parentid":111579669,"autoAddUser":false},{"tags":"grade","id":111684683,"createDeptGroup":false,"name":"二年级2017级","parentid":111579669,"autoAddUser":false},{"tags":"grade","id":111684684,"createDeptGroup":false,"name":"三年级2016级","parentid":111579669,"autoAddUser":false},{"tags":"grade","id":111684685,"createDeptGroup":false,"name":"四年级2015级","parentid":111579669,"autoAddUser":false},{"tags":"grade","id":111684686,"createDeptGroup":false,"name":"五年级2014级","parentid":111579669,"autoAddUser":false},{"tags":"grade","id":111684687,"createDeptGroup":false,"name":"六年级2013级","parentid":111579669,"autoAddUser":false},{"tags":"campus","id":111686554,"createDeptGroup":false,"name":"柏泰测试体验","parentid":-7,"autoAddUser":false},{"id":111686635,"createDeptGroup":true,"name":"子部门11","parentid":111611630,"autoAddUser":true},{"id":111692633,"createDeptGroup":false,"name":"老师","parentid":111554597,"autoAddUser":false},{"id":111692634,"createDeptGroup":false,"name":"学生","parentid":111554597,"autoAddUser":false},{"id":111692635,"createDeptGroup":false,"name":"家长","parentid":111554597,"autoAddUser":false},{"id":111692636,"createDeptGroup":false,"name":"老师","parentid":111554598,"autoAddUser":false},{"id":111692637,"createDeptGroup":false,"name":"学生","parentid":111554598,"autoAddUser":false},{"id":111692638,"createDeptGroup":false,"name":"家长","parentid":111554598,"autoAddUser":false},{"id":111692639,"createDeptGroup":false,"name":"老师","parentid":111554599,"autoAddUser":false},{"id":111692640,"createDeptGroup":false,"name":"学生","parentid":111554599,"autoAddUser":false},{"id":111692641,"createDeptGroup":false,"name":"家长","parentid":111554599,"autoAddUser":false},{"id":111692642,"createDeptGroup":false,"name":"老师","parentid":111554600,"autoAddUser":false},{"id":111692643,"createDeptGroup":false,"name":"学生","parentid":111554600,"autoAddUser":false},{"id":111692644,"createDeptGroup":false,"name":"家长","parentid":111554600,"autoAddUser":false},{"id":111692645,"createDeptGroup":false,"name":"老师","parentid":111554601,"autoAddUser":false},{"id":111692646,"createDeptGroup":false,"name":"学生","parentid":111554601,"autoAddUser":false},{"id":111692647,"createDeptGroup":false,"name":"家长","parentid":111554601,"autoAddUser":false},
        // {"id":111703536,"createDeptGroup":true,"name":"子部门22","parentid":111615520,"autoAddUser":true}],"errcode":0}
        if (httpResult.isSuccess()) {


            //TODO 依据返回的格式在做处理
        }
        return null;

    }


}
