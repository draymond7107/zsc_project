package com.zsc.base.auth;

import com.zsc.base.utils.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 功能说明：用户的基础类(缓存对象)<br>
 * 模块名称：NRBase<br>
 * 功能描述：UserSession<br>
 * 文件名称: UserSession.java<br>
 * 系统名称：ICELOVE<br>
 * 软件著作权：ICELOVE 版权所有<br>
 * 开发人员：lujun <br>
 * 开发时间：2016-10-16 下午3:13:10<br>
 * 系统版本：1.0.0<br>
 */
public class UserSession implements Serializable {
    private static final long serialVersionUID = 1L;
    protected Integer id;
    protected String userName;
    protected String nickName;
    //真实姓名
    protected String realName;
    //头像地址(这里保存的是已经经过处理后的完整URL)
    protected String imgurl;
    //用户等级
    protected Integer level;
    //记录最后登陆信息
    protected Integer loginClient;
    protected String deviceId;
    protected String loginImei;
    protected String pushToken;
    /******************一下是业务产生,存放在缓存的数据*******************/
    //登录授权
    protected String token;
    //登陆时间
    private Date loginTime;

    //一下无效字段
    protected Integer parentId;
    protected Integer kind;

    public UserSession() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getKind() {
        return kind;
    }

    public void setKind(Integer kind) {
        this.kind = kind;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getImgurl() {
        return imgurl;
    }

    //这里保存都是虚拟位置
    public void setImgurl(String imgurl) {
        if (StringUtils.isEmpty(imgurl)) {
            return;
        }
        this.imgurl = imgurl;
    }

    public int getLevel() {
        return level == null ? 0 : level.intValue();
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getLoginClient() {
        return loginClient;
    }

    public void setLoginClient(Integer loginClient) {
        this.loginClient = loginClient;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getLoginImei() {
        return loginImei;
    }

    public void setLoginImei(String loginImei) {
        this.loginImei = loginImei;
    }

    public String getPushToken() {
        return pushToken;
    }

    public void setPushToken(String pushToken) {
        this.pushToken = pushToken;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginTimeStr() {
        return loginTime == null ? "" : String.valueOf(loginTime.getTime());
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
