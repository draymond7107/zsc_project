package com.zsc.base.abs;
import com.alibaba.fastjson.JSONObject;
import com.zsc.base.Config;
import com.zsc.base.exception.SysException;
import com.zsc.base.utils.JsonUtils;
import com.zsc.base.vo.RetResult;
import com.zsc.constants.Constants;
import com.zsc.base.spring.JsonResult;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * SpringMVC的基础控制类
 * @author lujun
 */
public abstract class BaseController {
    protected final String PAGE_ERROR_404 = "/common/404.jsp";
    protected final String PAGE_ERROR_ERROR = "/common/error.jsp";
    protected Log logger = LogFactory.getLog(getClass());

    /**
     * 获取当前请求的Request对象
     * 一般情况这个式可以用的,但是文件表单就不行的
     *
     * @return
     */
    public HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }

    /**
     * 获取当前请求的Response对象
     * 一般情况这个式可以用的,但是文件表单就不行的
     *
     * @return
     */
    public HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
    }

//    //获取登录用户ID
//    protected AdminSession getAdminSession(){
//        return (AdminSession)getRequest().getAttribute(Constants.ADMIN_SESSION_KEY);
//    }
//    //获取登录用户ID
//    protected int getAdminId(){
//        AdminSession as = getAdminSession();
//        return as==null?0:as.getAdminId();
//    }

    /**
     * 当前请求中添加对象
     *
     * @param key
     * @param val
     */
    public void addAttribute(String key, Object val) {
        getRequest().setAttribute(key, val);
    }

    /**
     * 给当前的请求添加日志信息
     *
     * @param value
     */
    public void addAdminLog(String value) {
        getRequest().setAttribute(Constants.ADMIN_LOG_KEY, value);
    }

    public void addAdminLog(Integer userId, String value) {
        getRequest().setAttribute(Constants.ADMIN_LOG_USER_ID_KEY, userId);
        getRequest().setAttribute(Constants.ADMIN_LOG_KEY, value);
    }

    /**
     * 是否来自自己客户端访问
     *
     * @param request
     * @return
     */
    protected boolean fromPertechClient(HttpServletRequest request) {
        return isThisAgent(request, "PertechClient");
    }

    protected boolean fromWeiXin(HttpServletRequest request) {
        return isThisAgent(request, "MicroMessenger");
    }

    protected boolean isThisAgent(HttpServletRequest request, String agentName) {
        String header = request.getHeader("User-Agent");
        return header != null && header.contains(agentName);
    }

    /**
     * 判断用户浏览器信息
     *
     * @param request
     * @return
     */
    protected String getUserAgent(HttpServletRequest request) {
        return request.getHeader("User-Agent");
    }

    /**
     * ==================================================返回JsonResult==================================================
     **/
    protected JsonResult sendArgsError() {
        return new JsonResult(false, "提交参数错误");
    }

    protected JsonResult sendArgsError(String msg) {
        return new JsonResult(false, msg);
    }

    protected JsonResult sendException(RetResult<?, SysException> retResult) {
        SysException ex = retResult.getException();
        return new JsonResult(ex.getErrorCode(), ex.getErrorInfo());
    }

    protected JsonResult sendException(SysException exception) {
        return new JsonResult(exception.getErrorCode(), exception.getErrorInfo());
    }

    protected JsonResult sendResult(boolean result) {
        return new JsonResult(result, result ? "操作成功" : "操作失败");
    }

    protected JsonResult sendSuccess() {
        return new JsonResult(true, "操作成功");
    }

    protected JsonResult sendSuccess(String msg) {
        return new JsonResult(true, msg);
    }

    protected JsonResult sendSuccess(String msg, Object obj) {
        JsonResult result = new JsonResult(true, msg);
        result.setData(obj);
        return result;
    }

    protected JsonResult sendSuccess(Object obj) {
        JsonResult result = new JsonResult(true, "OK");
        result.setData(obj);
        return result;
    }

//    protected JsonResult sendError(SysException e) {
//        return new JsonResult(e.getErrorCode(), e.getErrorInfo());
//    }
//
//    protected JsonResult sendError(RetMsg retMsg) {
//        return new JsonResult(retMsg.getRet(), retMsg.getMsg());
//    }

    protected JsonResult sendError(String msg) {
        return new JsonResult(false, msg);
    }

    protected JsonResult sendError(Integer status, String msg) {
        JsonResult result = new JsonResult(false, msg);
        result.setRet(status);
        return result;
    }

    protected JsonResult sendError() {
        return new JsonResult(false, "诶呀！系统跑偏了！！！");
    }

    /**
     * 发送错误页面
     *
     * @param errorInfo
     * @return
     */
    protected String sendErrorPage(String errorInfo) {
        getRequest().setAttribute("errorInfo", errorInfo);
        return PAGE_ERROR_ERROR;
    }

    protected String sendErrorPage(String errorTitle, String errorInfo) {
        getRequest().setAttribute("errorTitle", errorTitle);
        getRequest().setAttribute("errorInfo", errorInfo);
        return PAGE_ERROR_ERROR;
    }

    protected String parseJsonHtml(JSONObject json) {
        return JsonUtils.toStringNoEx(json);
    }
    /**==================================================通用前端参数判断==================================================**/
    /**
     * 判断整数对象是否为null或者小于0
     *
     * @param val
     * @return
     */
    protected boolean isEmpty(Integer val) {
        return val == null || val.intValue() <= 0;
    }

    protected boolean notEmpty(Integer val) {
        return val != null && val.intValue() > 0;
    }

    protected boolean isEmpty(String str) {
        return StringUtils.isBlank(str);
    }

    protected boolean notEmpty(String str) {
        return StringUtils.isNotBlank(str);
    }

    /**
     * 获取当前分页
     **/
    protected int getPageNo() {
        //兼容firstRow写法
        int firstRow = getIntPar("firstRow", -1);
        int fetchSize = getIntPar("fetchSize", -1);
        if(firstRow > -1 && fetchSize > 0) {
            return (firstRow / fetchSize) + 1;
        }
        return getIntPar("pageNo", 1);
    }

    /**
     * 获取当前分页数
     **/
    protected int getPageSize() {
        int fetchSize = getIntPar("fetchSize", -1);
        if(fetchSize > 0) return fetchSize;
        return getIntPar("pageSize", 20);
    }

    protected int getIntPar(String parName) {
        return getIntPar(parName, 0);
    }

    protected int getIntPar(String parName, int defaultNum) {
        String parVal = getRequest().getParameter(parName);
        int par = 0;
        try {
            if(StringUtils.isNotEmpty(parVal)) {
                parVal = StringUtils.trim(parVal);
                par = Integer.parseInt(parVal);
            } else {
                par = defaultNum;
            }
        } catch (Exception e) {
            par = defaultNum;
        }
        return par;
    }

    public String getStrPar(String param) {
        return getStrPar(param, "");
    }

    /**
     * 判断是否存在参数
     **/
    public boolean parIllegal(String val) {
        return val == null || val.trim().length() == 0;
    }

    public boolean parIllegal(Integer val) {
        return val == null || val < 1;
    }

    public boolean parIsNull(String key) {
        return getRequest().getParameter(key) == null;
    }

    /**
     * 判断是否有参数存在
     *
     * @param key
     * @return
     */
    public boolean hasParameter(String key) {
        return getRequest().getParameterMap().containsKey(key);
    }

    public boolean noParameter(String key) {
        return !getRequest().getParameterMap().containsKey(key);
    }

    public String getStrPar(String param, String defaultValue) {
        String value = getRequest().getParameter(param);
        value = (StringUtils.isEmpty(value)) ? defaultValue : value.trim();
        return value;
    }

    protected void sendHtml(String html){
        HttpServletResponse response = getResponse();
        try{
            response.setCharacterEncoding(Config.ENC_UTF);
            response.getWriter().println(html);
            response.getWriter().close();
        }catch (Exception e){
            logger.error(e);
        }
    }

    /**
     * ==================================================URL跳转==================================================
     **/
    protected String redirect(String url) {
        return "redirect:" + url;
    }

    protected String forward(String url) {
        return "forward:" + url;
    }
    /**==================================================对当前的URL进行编码==================================================**/
    /**
     * 对字符串进行编码
     *
     * @param data
     * @return
     */
    protected String urlEncoder(String data) {
        try {
            return java.net.URLEncoder.encode(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "encode error";
        }
    }

    /**
     * 对字符串进行解码
     *
     * @param data
     * @return
     */
    protected String urlDecoder(String data) {
        try {
            return java.net.URLDecoder.decode(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "encode error";
        }
    }
    /**
     * 获取当前请求的携带的openId
     * @return
     */
  /*  protected String getOpenId() throws TokenException{
        String openId = (String)getRequest().getAttribute("openId");
        if(StringUtils.isEmpty(openId)){
            throw new TokenException("获取Token失败");
        }
        String[] strs = StringUtils.split(openId,"-");
        if(strs==null || strs.length!=2){
            throw new TokenException("获取Token失败");
        }
        return strs[0];
    }*/

}
