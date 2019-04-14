package com.zsc.base.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.net.URLEncoder;
import java.util.*;

/**
 * ClassName:签名用的工具箱
 *
 * @author
 */
public class SignUtils {
    /**
     * <一句话功能简述> <功能详细描述>验证返回参数
     * 参数校验并且将key
     *
     * @param params
     * @param key
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static boolean checkParam(Map<String, String> params, String key) {
        boolean result = false;
        if (params.containsKey("sign")) {
            String sign = params.get("sign");
            params.remove("sign");
            StringBuilder buf = new StringBuilder((params.size() + 1) * 10);//为什么这样定义容量
            SignUtils.buildPayParams(buf, params, false, true); //将Map装换为String(key,value)&形式
            String preStr = buf.toString() + "&key=" + key;
            String signRecieve = CryptUtils.md5(preStr, "utf-8");  //MD5加密
            result = sign.equalsIgnoreCase(signRecieve);//接收的sign与后台将参数加密生成的sign对比
        }
        return result;
    }

    /**
     * 过滤参数
     *将value为null、""、sign的过滤
     * @param sArray
     * @return
     * @author
     */
    public static Map<String, String> paraFilter(Map<String, String> sArray) {
        Map<String, String> result = new HashMap<String, String>(sArray.size());
        if (sArray == null || sArray.size() <= 0) {
            return result;
        }
        for (String key : sArray.keySet()) {
            String value = sArray.get(key);
            if (value == null || "".equals(value) || "sign".equalsIgnoreCase(key)) {  //sign是计算得出的，不能作为实际意义的参数
                continue;
            }
            result.put(key, value);
        }
        return result;
    }

    /**
     * <一句话功能简述> <功能详细描述>将map转成String
     *
     * @param payParams
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String payParamsToString(Map<String, String> payParams) {
        return payParamsToString(payParams, false);
    }

    public static String payParamsToString(Map<String, String> payParams, boolean encoding) {
        return payParamsToString(new StringBuilder(), payParams, encoding);
    }

    /**
     * @param payParams
     * @return
     * @author
     */
    public static String payParamsToString(StringBuilder sb, Map<String, String> payParams, boolean encoding) {
        buildPayParams(sb, payParams, encoding, true);
        return sb.toString();
    }

    /**
     * 将payParams的参数追加到sb
     *
     * @param payParams
     * @return
     * @author
     */
    public static void buildPayParams(StringBuilder sb, Map<String, String> payParams, boolean encoding, boolean removeNull) {
        List<String> keys = new ArrayList<String>(payParams.keySet());  //map的key的List
        Collections.sort(keys);  //排序
        for (String key : keys) {
            if (removeNull) {  //为ture时校验Map是否有null值（Map的key与value都可以为null）
                if (StringUtils.isEmpty(payParams.get(key))) {
                    continue;
                }
            }
            sb.append(key).append("=");
            if (encoding) {
                sb.append(urlEncode(payParams.get(key))); //使用UTF-8解码
            } else {
                sb.append(payParams.get(key));
            }
            sb.append("&");
        }
        sb.setLength(sb.length() - 1);      //为什么-1
    }

    public static String urlEncode(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (Throwable e) {
            return str;
        }
    }

    public static Element readerXml(String body, String encode) throws DocumentException {
        SAXReader reader = new SAXReader(false);
        InputSource source = new InputSource(new StringReader(body));
        source.setEncoding(encode);
        Document doc = reader.read(source);
        Element element = doc.getRootElement();
        return element;
    }
}
