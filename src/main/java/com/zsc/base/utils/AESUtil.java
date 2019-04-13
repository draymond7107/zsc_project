package com.zsc.base.utils;

import org.springframework.util.DigestUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * 文件对象
 * 功能说明：<br>
 * 模块名称：<br>
 * 功能描述：<br>
 * 文件名称: <br>
 * 系统名称：<br>
 * 软件著作权：icelove 版权所有<br>
 * 开发人员：icelove <br>
 * 开发时间：2019/3/4 11:43<br>
 * 系统版本：1.0.0<br>
 */
public class AESUtil {
    /**
     * 密钥算法
     */
    private static final String ALGORITHM = "AES";
    /**
     * 加解密算法/工作模式/填充方式
     */
    private static final String ALGORITHM_MODE_PADDING = "AES/ECB/PKCS5Padding";
    /**
     * 生成key
     */
    private static SecretKeySpec key = new SecretKeySpec(org.apache.commons.codec.digest.DigestUtils.md5Hex("ce2e43302b13104b031c49b2016c1594").toLowerCase().getBytes(), ALGORITHM);
    /**
     * AES加密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static String encryptData(String data) throws Exception {
        // 创建密码器
        Cipher cipher = Cipher.getInstance(ALGORITHM_MODE_PADDING);
        // 初始化
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return CryptUtils.base64Encode(cipher.doFinal(data.getBytes()));
    }
    /**
     * AES解密
     *
     * @param base64Data
     * @return
     * @throws Exception
     */
    public static String decryptData(String base64Data) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM_MODE_PADDING);
        cipher.init(Cipher.DECRYPT_MODE, key);
        return new String(cipher.doFinal(CryptUtils.base64Decode2Byte(base64Data)));
    }
    public static void main(String[] args) throws Exception {
        //解密 String req_info="Ih5osM/5IbPfHouVrUmwebd1yAW2Gys91jv006W1237sSi3z022KxHafLIDMrQLYiBttTadgvy2cbx6DnmwDIQ52lPWfo6pAAHt7Q9DjBIpDRQ7JsbEBlomoQP2ZkdNHnWscVYuFEVlItaSlkSlcKLdB4UwMduqDYseFsUUthz6htPeBu987zXS6dKrgIbRwOxt5RfPmk1sf0oVB2yU3UH0Ly8SzBjmN1jrh4qAaUkfH6VkeMJcsZSGchQn2VresxJTbGH++JE1UsXUF3gyYpweyxBPtHoKdaggsIONR20UKNxJYPJLnEOnfQF/Ipmk8/QmTVRK7iqfVLC9EA1Auma0AlKBjZlYqynUlF3y+E2ZzgWMUlvDHZVWDbzp/TcE0q+Ukc7yQ3HBsibDR474SPlLTkCWz1iydXzkVcLqJKamsh76Liv1a0hzu0sI3qasMAfmwU6/q7/N6quq031toO1GxqkVaxBRK7e64gSOx9ArxxVFgZ7WN+JPq2OH/pTKH8ToxHA0rtxN5+aAgZGkXiIOUiHtp4mjpRxqe34WK7C7Nr0DQyOVwsXT2TTegSgWGm34aa//ZYxHedubv2iX+E7K222lptg9IqHlMXBbwKFtKtIcal61+8ciz+sB1FBpqHchC+3whTqWv5ZANiHBzaOhbIbA/mKX2XZ6Cy0iYh+bL/8Y/Hvz/UnMGzor+2anIUeBAGRQmseL4jY+Qic46WLuEhDcarCaO4JgJSAOC+VmsdrER9TRum26PFwTQwtNpxkrKCiO9Gv36Ood5D8hXnLHUH+4nbsek8ouxkCcFXq4Us0mipB3i5ksQpt23LiJm9Ahxyvptp9Q41SytS48NXiz3IxTOqDdknowedZwAtJ/fhBlwiOHD9N+pECXuNBKLaCZcatGycr0/DPELiCF+MIRQ6V60wzaZD74TKRFULd1ljNsoQIAbuGaT40WMDY6a28jBHQ
        // /IXnD4gvSvfeumwQzp3Q9PiPyFtF6JxH7RBRj9/lmQuQozJIPZCaCNVTBfWQOdcFaBnPLN0ZNvzjA93g6jcIxHzkXHmiGfh98vq2E=";
        String req_info = "RjQCXzEnAlg+uYhcKQ+3sdPMdDPGk8zhbvsVFTAiO9tsDb8jUw+Z35CyV0P7xslCMir4VIhl1j/Q7/RrE2oIXs74OpY7C7wLSsnATIEl6PCjnpyYs3imKOkt9tp7inMg4njDYIJOhr4sfDZ0vIH22aZihJKsYoRTvbckN7DYu9rEUcm/z40VP9o+NiRKEhjZmQeXzoM8xcOyVqjaiy2Eu3Q2ll49eqo8M2pY8UCAGLqZwvx6kkwQKpColMry30Nse1snkFBtf7W3HcKlZYPLHo4oGNLZZvELo2+rz82k45BGf4yWpeMoS67tN0qJzKLba5wPbfh/dIuMMUinMkMOYXZvpdTH4HrXmmZp2hhs1PA7Uup83CXx4AisOvlgjvRxzhDzX9prlF3vkO5xZq3jlKbCR8BGUHkflnJgBSyyFVNeDLgdfiacio65zMk8aWJ+/XqqCL6eXBpS+v49IkiP1zgLNIcwCId851cBKcwg1bFaZ1nx+9iC3YasbYqTclIA9Mu7RXMhIUNOPBgg5/MB6PKzyxbSO02/zfxk48q16jiSss51yT2KYIeMGbJUVAH4PF4KjD0/LRtuE7TDsTOSb7Ch6y9oPYxFPS9UKqOM6roxYSeILmfB/8Fu99kwIGZEuv+jeB4N5lgMczujdt2GPhGCr9TGKhQy3s5RweLDTlJ+TCJR/CNqbOqhp6mxF7CL3hMQ8hTg/GRYuCxXvCwtOOlq28TRYH8Bbk5bxz1GiOfmVwH/lK0cU2BhAOb4Idy8dYKuxhtRZZjOmZ3W3uVWzu8tfWzRxCPUvJO6gvECwMgAZCDMeQz5tI1fD47uNojVhu4E9zxt9I8QEHQQ18JsdckUwEDldPexIqiWPmOOOediN1UbDxFee5Nf4mU669qkWMUi3X2/p4DtnIXvJpsqqpzqSYELyLQGaMT00OxpSfMTHa+6IChH8Q78iUYPmGxysRFLBCZanWGSuUqevJR23oR5fmu/MAXoDM2CDafZcgqhE/W1l/mK6QbRmB0RdYoOAGjNwO185Y+K+ut0F5DVN44O3YG4N0eOLqgZ/8JEOjtPxRL1xbWfYKGQwvttDezfxEcFYFhKHEPUt+KoLEPo0Q==";
        String B = AESUtil.decryptData(req_info);
        System.out.println(B);
        //加密
        //String str = "<root>" + "<out_refund_no><![CDATA[2531340110812300]]></out_refund_no>" + "<out_trade_no><![CDATA[2531340110812100]]></out_trade_no>" + "<refund_account><![CDATA[REFUND_SOURCE_RECHARGE_FUNDS]]></refund_account>" + "<refund_fee><![CDATA[1]]></refund_fee>" + "<refund_id><![CDATA[50000505542018011003064518841]]></refund_id>" + "<refund_recv_accout><![CDATA[支付用户零钱]]></refund_recv_accout>" + "<refund_request_source><![CDATA[API]]></refund_request_source>" + "<refund_status><![CDATA[SUCCESS]]></refund_status>" + "<settlement_refund_fee><![CDATA[1]]></settlement_refund_fee>" + "<settlement_total_fee><![CDATA[1]]></settlement_total_fee>" + "<success_time><![CDATA[2018-01-10 10:31:24]]></success_time>" + "<total_fee><![CDATA[1]]></total_fee>" + "<transaction_id><![CDATA[4200000052201801101409025381]]></transaction_id>" + "</root>";
        //System.out.println(encryptData(str));
    }
}
