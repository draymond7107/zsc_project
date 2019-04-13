package com.zsc.base.vo;

import com.zsc.base.utils.StringUtils;

import java.io.Serializable;

/**
 * 客户端请求包含的信息
 *
 * @author lujun
 */
public class ClientInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String product;       //客户端产品:edu
    private String clientType;    //客户端类型(前端传过来是名称,数据库是client的整形):iphone,android
    private String ver;           //版本信息:2.0.1
    private String apiVer;        //API接口版本信息:1.1
    private Integer apiLevel;     //API接口版本:26
    private String deviceId;      //客户端端唯一码:CRBDD2FD-9B61-4438-83A8-158DB2464DSB
    private String remoteIp;      //远程请求的Ip
    private String token;         //用户登录信息TOKEN;
    private String sign;          //请求的签名
    private String channel;       //渠道包信息:CPS合作等用户(渠道号由UMENG_CHANNEL值分离出来自己的包是lfcp_xiaomi:代表自己的渠道小米市场的包)
    private String market;        //请求包的所属市场:appstore,小米,华为(传过来的值=lfcp_xiaomi)

    public ClientInfo() {
    }

    public void init() {
        if(StringUtils.isEmpty(product)) product = "";
        if(StringUtils.isEmpty(clientType)) clientType = "";
        if(StringUtils.isEmpty(ver)) ver = "";
        if(StringUtils.isEmpty(channel)) channel = "";
        if(StringUtils.isEmpty(apiVer)) apiVer = "";
        if(apiLevel == null) apiLevel = 0;
        if(StringUtils.isEmpty(deviceId)) deviceId = "";
        if(StringUtils.isEmpty(remoteIp)) remoteIp = "";
        if(StringUtils.isEmpty(token)) token = "";
        if(StringUtils.isEmpty(sign)) sign = "";
    }

//    public boolean isAndroid() {
//        return ClientUtils.isAndroid(this.clientType);
//    }
//
//    public boolean isIphone() {
//        return ClientUtils.isIphone(this.clientType);
//    }
//
//    public boolean isWap() {
//        return ClientUtils.isWap(this.clientType);
//    }
//
//    public boolean isWeb() {
//        return ClientUtils.isWeb(this.clientType);
//    }
//
//    //获取int类型的客户端类别
//    public int getClientIntVal() {
//        return ClientUtils.getClient(this.clientType);
//    }

    //版本号
//    public int getVersionInt() {
//        String version = this.getVer();
//        int versionInt = 0;
//        try {
//            versionInt = VersionUtils.verStr2Int(version);
//        } catch (Exception e) {
//            return -1;
//        }
//        return versionInt;
//    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getClientType() {
        return clientType;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getApiVer() {
        return apiVer;
    }

    public void setApiVer(String apiVer) {
        this.apiVer = apiVer;
    }

    public Integer getApiLevel() {
        return apiLevel;
    }

    public void setApiLevel(Integer apiLevel) {
        this.apiLevel = apiLevel;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public void setRemoteIp(String remoteIp) {
        this.remoteIp = remoteIp;
    }

    public String getRemoteIp() {
        return remoteIp;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    //获取推广上
    public String getChannel() {
        if(StringUtils.isNotEmpty(this.market) && this.market.contains("_")) {
            return this.market.split("_")[0];
        }
        return "pertech";
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getMarket() {
        return market;
    }
}
