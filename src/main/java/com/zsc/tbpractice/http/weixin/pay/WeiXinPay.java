package com.zsc.tbpractice.http.weixin.pay;


import com.alibaba.fastjson.JSONObject;
import com.zsc.base.Config;
import com.zsc.base.abs.BaseController;
import com.zsc.base.http.HttpResult;
import com.zsc.base.http.HttpUtils;
import com.zsc.base.utils.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author ZhangSuchao
 * @create 2019/4/14
 * @since 1.0.0
 */
@RestController
@RequestMapping("/weixin")
public class WeiXinPay extends BaseController {

    private Log logger = LogFactory.getLog(WeiXinPay.class);
    //请求微信下单地址
    private String REQUEST_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    private String REQUEST_URL_REFUND = "https://api.mch.weixin.qq.com/secapi/pay/refund";
    //微信支付下单回掉接口
    private String NOTIFY_URL = Config.getSoftDomain() + "/weixin/pay_notify";
    //微信支付退款回掉接口
    private String NOTIFY_URL_REFUND = Config.getSoftDomain() + "/weixin/refund_notify";




    @RequestMapping("/pay")
    public Object weixinPayTest() throws Exception {
        //头信息
        List<Header> headers = new ArrayList<Header>();
        headers.add(new BasicHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8"));
        headers.add(new BasicHeader("Accept-Encoding", "gzip, deflate, sdch"));
        headers.add(new BasicHeader("Connection", "keep-alive"));
        headers.add(new BasicHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6,zh-TW;q=0.4"));
        headers.add(new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36"));
        //请求体
        Date now = new Date();
        String remoteIp = RequestUtils.getRemoteIp(getRequest());
        //    String remoteIp = "123.12.12.123";

        SortedMap<String, String> params = new TreeMap<String, String>();  //有排序的Map
        ////微信分配的公众账号ID（企业号corpid即为此appId）
        params.put("appid", "wx5ee8a534b633e274");
        //微信支付分配的商户号
        params.put("mch_id", "1525509791");
        //设备号 终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB"
        //params.put("device_info","WEB");
        //随机字符串 随机字符串，不长于32位。推荐随机数生成算法
        params.put("nonce_str", RandomUtils.uuid());
        //签名类型 签名类型，目前支持HMAC-SHA256和MD5，默认为MD5
        params.put("sign_type", "MD5");
        //商品描述 腾讯充值中心-QQ会员充值
        params.put("body", "课程的订单-教育课程");
        //商品详细 腾讯充值中心-QQ会员充值
        params.put("detail", "课程的订单-数学");
        //附加数据 深圳分店 附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
        params.put("attach", "[org=1]");
        //out_trade_no商户订单号 商户系统内部的订单号,32个字符内、可包含字母, 其他说明见商户订单号
        params.put("out_trade_no", "20190402143248305NEYCGC7VYV6QM");
        //货币类型 符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
        params.put("fee_type", "CNY");
        //标价金额
        params.put("total_fee", String.valueOf((int) (0.01 * 100)));
        //终端IP 必须传正确的用户端IP,支持ipv4、ipv6格式，获取方式详见
        params.put("spbill_create_ip", remoteIp);
        //交易起始时间 订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
        //交易结束时间time_expire	否	String(14)	20091227091010 订单失效时间，格式为yyyyMMddHHmmss，
        params.put("time_start", DateUtils.format("yyyyMMddHHmmss", now));
        //如2009年12月27日9点10分10秒表示为20091227091010。其他详见时间规则注意：最短失效时间间隔必须大于5分钟
        now = DateUtils.addHour(now, 1);
        params.put("time_expire", DateUtils.format("yyyyMMddHHmmss", now));
        //商品标记
        params.put("goods_tag", "1");
        params.put("notify_url", NOTIFY_URL);
        //交易类型 H5使用MWEB
        params.put("trade_type", "MWEB");
        //商品ID(课程id)
        params.put("product_id", "1");
        //指定支付方式
        //params.put("limit_pay","");
        //用户标识
        //params.put("openid", "123123123");
        //场景信息
        JSONObject scene_info = new JSONObject();
        JSONObject h5_info = new JSONObject();
        h5_info.put("type", "Wap");
        h5_info.put("wap_url", "http://dev.pertech.cn");
        h5_info.put("wap_name", "甬工惠");
        scene_info.put("h5_info", h5_info);
        params.put("scene_info", JsonUtils.toStringNoEx(scene_info));
        StringBuilder buf = new StringBuilder();
        SignUtils.buildPayParams(buf, params, false, true);   //将Map转换成StringBuilder，不适用解码，去null
        String preStr = buf.toString();
        //logger.error("<payRequest> 签名数据:\n" + preStr + "&key=" + platPayDO.getPayKey());
        String sign = CryptUtils.md5(preStr + "&key=" + "ce2e43302b13104b031c49b2016c1594".toUpperCase());
        //logger.error("<payRequest> 签名值: " + sign);
        params.put("sign", sign);
        String data = XmlUtils.map2Xml(params);


        logger.error("调用微信支付的接口");
        HttpResult result = HttpUtils.post(REQUEST_URL, data, "utf-8", headers);
        System.out.println(result);
        return result;
    }

}
