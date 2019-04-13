package com.zsc.base.utils;

import com.zsc.base.Config;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 一些常用的加密类
 *
 * @author lujun
 */
public class CryptUtils {
    /**
     * 如果系统中存在旧版本的数据，则此值不能修改，否则在进行密码解析的时候出错
     */
    private final static String KEY = "_Sh66oP_";// 系统默认密匙8个长度
    private final static String DES = "DES";// 采用DES算法，不是密匙
    private final static String utf8 = "utf-8";
    /**
     * AES加密字符串
     *
     * @param content 需要被加密的字符串
     * @param password 加密需要的密码
     * @return 密文(返回字符串经过base64加密)
     */
    public static String AES = "AES";
    public static int AES_LEN = 128;
    //加解密算法/工作模式/填充方式
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    /**
     * 签名一个RUL数据
     *
     * @param data
     * @param key
     * @return
     */
    public static String signUrlData(CharSequence data, String key) {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String pars = data.toString();
        if (StringUtils.isBlank(pars)) {
            pars = "timestamp=" + timestamp;
        } else {
            pars += "&timestamp=" + timestamp;
        }
        List<String> list = new ArrayList<String>();
        list.add(key);
        String[] items = StringUtils.split(pars, "&");
        for (String item : items) {
            if (item == null || item.trim().length() == 0) continue;
            String[] kv = item.split("=", 2);
            if (kv == null || kv.length < 1) continue;
            String pk = kv[0];
            String pv = kv[1] == null ? "" : kv[1];
            if (!"sign".equals(pk)) {
                list.add(pv);
            }
        }
        SortUtils.sortList(list, true);
        String sign = md5(list.toString(), Config.ENC_UTF);
        if (sign == null) {
            throw new RuntimeException("签名URL失败");
        }
        return pars + "&sign=" + sign;
    }

    public static boolean validUrlsign(Map<String, String[]> parameterMap, String key) {
        if (parameterMap == null || parameterMap.isEmpty() || key == null) return false;
        Set<Entry<String, String[]>> entrySet = parameterMap.entrySet();
        String sign = "";
        List<String> list = new ArrayList<String>();
        list.add(key);
        for (Iterator<Entry<String, String[]>> it = entrySet.iterator(); it.hasNext(); ) {
            Entry<String, String[]> entry = it.next();
            String pk = entry.getKey().toString();
            String pv = ((String[]) entry.getValue())[0];
            if ("sign".equalsIgnoreCase(pk)) {
                sign = pv;
            } else {
                list.add(pv);
            }
        }
        if (StringUtils.isEmpty(sign)) {
            return false;
        } else {
            SortUtils.sortList(list, true);
            String md5Str = md5(list.toString(), Config.ENC_UTF);
            return sign.equals(md5Str);
        }
    }

    /**
     * 获取一个map的签名信息
     *
     * @param map
     * @param key
     * @return
     */
    public static String signMap(Map<String, Object> map, String key) {
        if (map == null || map.isEmpty() || key == null) return "";
        Set<Entry<String, Object>> entrySet = map.entrySet();
        List<String> list = new ArrayList<String>();
        list.add(key);
        for (Iterator<Entry<String, Object>> it = entrySet.iterator(); it.hasNext(); ) {
            Entry<String, Object> entry = it.next();
            String pk = entry.getKey();
            String pv = String.valueOf(entry.getValue());
            if (!"sign".equalsIgnoreCase(pk)) {
                list.add(pv);
            }
        }
        SortUtils.sortList(list, true);
        return md5(list.toString(), Config.ENC_UTF);
    }

    //中文转UNICODE
    public static String encodeToUnicode(final String gbString) {
        char[] utfBytes = gbString.toCharArray();
        String unicodeBytes = "";
        for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {
            String hexB = Integer.toHexString(utfBytes[byteIndex]); //转换为16进制整型字符串
            if (hexB.length() <= 2) {
                hexB = "00" + hexB;
            }
            unicodeBytes = unicodeBytes + "\\u" + hexB;
        }
        return unicodeBytes;
    }

    //UNICODE转中文
    public static String decodeUnicode(final String dataStr) {
        int start = 0;
        int end = 0;
        final StringBuffer buffer = new StringBuffer();
        while (start > -1) {
            end = dataStr.indexOf("\\u", start + 2);
            String charStr = "";
            if (end == -1) {
                charStr = dataStr.substring(start + 2, dataStr.length());
            } else {
                charStr = dataStr.substring(start + 2, end);
            }
            //16进制parse整形字符串。
            char letter = (char) Integer.parseInt(charStr, 16);
            buffer.append(new Character(letter).toString());
            start = end;
        }
        return buffer.toString();
    }

    /**
     * URL加密
     **/
    public static String urlEncode(String str) {
        if (StringUtils.isEmpty(str)) return str;
        return urlEncode(str, Config.ENC_UTF);
    }

    public static String urlEncode(String str, String enc) {
        if (StringUtils.isEmpty(str)) return str;
        try {
            return java.net.URLEncoder.encode(str, enc);
        } catch (UnsupportedEncodingException ex) {
            return "";
        }
    }

    /**
     * URL解密
     **/
    public static String urlDecode(String str) {
        return urlDecode(str, Config.ENC_UTF);
    }

    public static String urlDecode(String str, String enc) {
        try {
            return java.net.URLDecoder.decode(str, Config.ENC_UTF);
        } catch (UnsupportedEncodingException ex) {
            return "";
        }
    }

    public static String md5Safe(String inbuf) {
        return md5Safe(inbuf, "UTF-8");
    }

    /**
     * sun系统自带的MD5加密
     *
     * @param inbuf
     * @return
     */
    public static String md5(String inbuf) {
        return md5(inbuf, "UTF-8");
    }

    /**
     * 经过换序的MD5
     *
     * @param inbuf
     * @param charset
     * @return
     */
    public static String md5Safe(String inbuf, String charset) {
        try {
            String md5 = md5(inbuf, charset);
            //0-8,8-16,16-24,24-32
            return md5.substring(16, 24) + md5.substring(0, 8) + md5.substring(24, 32) + md5.substring(16, 24);
        } catch (Exception e) {
            return null;
        }
    }

    public static String md5(String inbuf, String charset) {
        try {
            MessageDigest dg = MessageDigest.getInstance("MD5");
            byte[] result = dg.digest(inbuf.getBytes(charset));
            StringBuilder sb = new StringBuilder();
            for (byte bit : result) {
                sb.append(String.format("%02x", bit));
            }
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 利用java原生的摘要实现SHA256加密
     *
     * @param str 加密后的报文
     * @return
     */
    public static String sha256(String str) {
        MessageDigest messageDigest;
        String encodeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            encodeStr = byte2hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodeStr;
    }

    /**
     * BASE64 编码
     */
    public static String base64Encode(String src) {
        byte[] b = src.getBytes();
        Base64 base64 = new Base64();
        b = base64.encode(b);
        String s = new String(b);
        return s;
    }

    public static String base64Encode(byte[] b) {
        Base64 base64 = new Base64();
        b = base64.encode(b);
        String s = new String(b);
        return s;
    }

    /**
     * BASE64 解码
     */
    public static String base64Decode(String src) {
        return base64Decode(src, Config.ENC_UTF);
    }

    public static String base64Decode(String src, String enc) {
        try {
            byte[] b = src.getBytes(enc);
            b = new Base64().decode(b);
            return new String(b);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] base64Decode2Byte(String src) {
        return base64Decode2Byte(src, Config.ENC_UTF);
    }

    public static byte[] base64Decode2Byte(String src, String enc) {
        try {
            byte[] b = src.getBytes(enc);
            return new Base64().decode(b);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 默认加密方式
     *
     * @param data
     * @return
     */
    public final static String encryptDES(String data) {
        return encryptDES(data, KEY);
    }

    /**
     * 默认解密方式
     *
     * @param data
     * @return
     */
    public final static String decryptDES(String data) {
        return decryptDES(data, KEY);
    }

    /**
     * 数据加密
     *
     * @param data
     * @param key  密钥
     * @return
     * @throws Exception
     */
    public final static String encryptDES(byte[] data, String key) {
        if (data != null) try {
            return byte2hex(encrypt(data, key.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public final static String encryptDES(String data, String key) {
        if (data != null) try {
            return byte2hex(encrypt(data.getBytes(), key.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 数据解密
     *
     * @param data
     * @param key  密钥
     * @return
     * @throws Exception
     */
    public final static String decryptDES(byte[] data, String key) {
        if (data != null) try {
            return new String(decryptDES(hex2byte(data), key.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public final static String decryptDES(String data, String key) {
        if (data != null) try {
            return new String(decryptDES(hex2byte(data.getBytes()), key.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 加密
     *
     * @param src 数据源
     * @param key 密钥，长度必须是8的倍数
     * @return 返回加密后的数据
     * @throws Exception
     */
    public static byte[] encrypt(byte[] src, byte[] key) throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 从原始密匙数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
        // 创建一个密匙工厂，然后用它把DESKeySpec转换成
        // 一个SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(DES);
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
        // 现在，获取数据并加密
        // 正式执行加密操作
        return cipher.doFinal(src);
    }

    /**
     * 解密
     *
     * @param src 数据源
     * @param key 密钥，长度必须是8的倍数
     * @return 返回解密后的原始数据
     * @throws Exception
     */
    public static byte[] decryptDES(byte[] src, byte[] key) throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 从原始密匙数据创建一个DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
        // 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
        // 一个SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(DES);
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
        // 现在，获取数据并解密
        // 正式执行解密操作
        return cipher.doFinal(src);
    }

    /**
     * 二行制转字符串
     *
     * @param b
     * @return
     */
    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; b != null && n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) hs = hs + "0" + stmp;
            else hs = hs + stmp;
        }
        return hs.toUpperCase();
    }

    public static byte[] hex2byte(byte[] b) {
        if ((b.length % 2) != 0) throw new IllegalArgumentException("长度不是偶数");
        byte[] b2 = new byte[b.length / 2];
        for (int n = 0; n < b.length; n += 2) {
            String item = new String(b, n, 2);
            b2[n / 2] = (byte) Integer.parseInt(item, 16);
        }
        return b2;
    }
    /******************************************AES加密解密**************************************************/
    /**
     * 该方法返回一个字符的DBCS编码值
     *
     * @param cc
     * @return int
     */
    public static int getCode(char cc) {
        byte[] bs = String.valueOf(cc).getBytes();
        int code = (bs[0] << 8) | (bs[1] & 0x00FF);
        if (bs.length < 2) code = (int) cc;
        bs = null;
        return code;
    }

    public static boolean isAscii(char ch) {
        return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9');
    }

    public static byte[] encryptAES(String content, String key) {
        if (content == null) return null;
        try {
            return encryptAES(content.getBytes(Config.ENC_UTF), key);
        } catch (Exception e) {
            return null;
        }
    }

    public static byte[] encryptAES(byte[] content, String key) {
        try {
            //创建AES的Key生产者
            KeyGenerator kgen = KeyGenerator.getInstance(AES);
            //利用用户密码作为随机数初始化出
            kgen.init(AES_LEN, new SecureRandom(key.getBytes(Config.ENC_UTF)));
            //128位的key生产者
            //加密没关系,SecureRandom是生成安全随机数序列，password.getBytes()是种子，只要种子相同，序列就一样，所以解密只要有password就行
            //根据用户密码，生成一个密钥
            SecretKey secretKey = kgen.generateKey();
            //返回基本编码格式的密钥，如果此密钥不支持编码，则返回null。
            byte[] enCodeFormat = secretKey.getEncoded();
            // 转换为AES专用密钥
            SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, AES);
            //创建密码器
            Cipher cipher = Cipher.getInstance(AES);
            //初始化为加密模式的密码器
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            //加密
            return cipher.doFinal(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密AES加密过的字符串
     * 默认的试用密码随机加密方式
     * @param content AES加密过过的内容
     * @param key     加密时的密码
     * @return 明文
     */
    public static byte[] decryptAES(String content,String key) {
        if (content == null) return null;
        try {
            SecretKeySpec secretKeySpec = getSecretKeyAES(key);
            return decryptAES(content.getBytes(Config.ENC_UTF),secretKeySpec);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 解密AES加密过的字符串
     * @param content AES加密过过的内容
     * @param secretKeySpec 加密时的密码
     * @return
     */
    public static byte[] decryptAES(byte[] content, SecretKeySpec secretKeySpec) {
        if (content == null) return null;
        try {
            Security.addProvider(new BouncyCastleProvider());
            //创建密码器
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            //初始化为解密模式的密码器
            cipher.init(Cipher.DECRYPT_MODE,secretKeySpec);
            return cipher.doFinal(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 密码生成方式 密码做MD5加密方式(微信支付的加密方式)
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static SecretKeySpec getSecretKeyMD5AES(final String password) throws NoSuchAlgorithmException {
        //创建AES的Key生产者
        String mymd5 = CryptUtils.md5(password).toLowerCase();
        //转换成AES的密钥
        return new SecretKeySpec(mymd5.getBytes(),AES);
    }

    /**
     * 密码生成方式
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static SecretKeySpec getSecretKeyAES(final String password) throws NoSuchAlgorithmException {
        //创建AES的Key生产者
        KeyGenerator kgen = KeyGenerator.getInstance(AES);
        kgen.init(AES_LEN, new SecureRandom(password.getBytes()));
        //生成密钥
        SecretKey secretKey = kgen.generateKey();
        //转换成AES的密钥
        return new SecretKeySpec(secretKey.getEncoded(),AES);
    }
    /******************************************自定义的int加密和解密**************************************************/
    //简单的位运算加密
    public static String bitEncode(String key, byte[] b) throws Exception {
        byte[] keys = key.getBytes(utf8);
        for (int i = 0, size = b.length; i < size; i++) {
            for (byte keyBytes0 : keys) {
                b[i] = (byte) (b[i] ^ keyBytes0);
            }
        }
        String result = new String(new Base64().encode(b));
        //处理base64=+问题
        //= >>>> -  +>>>_
        //result = result.replaceAll("\\+","_");
        return result;
    }

    //简单的位运算解密
    public static String bitDecode(String key, String dec) throws Exception {
        dec = dec.replaceAll("\\_", "+");
        byte[] source = dec.getBytes(utf8);
        source = new Base64().decode(source);
        byte[] keys = key.getBytes(utf8);
        byte[] dee = source;
        for (int i = 0, size = source.length; i < size; i++) {
            for (byte keyBytes0 : keys) {
                source[i] = (byte) (dee[i] ^ keyBytes0);
            }
        }
        return new String(source);
    }

    /**
     * 一个int补位再进行位运算加密
     *
     * @param key
     * @param num
     * @return
     * @throws Exception
     */
    public static String intBitEncode(String key, int num) throws Exception {
        byte[] unitByte = new byte[6];
        byte[] src = ByteBufferUtils.toBytes(num);
        System.arraycopy(src, 0, unitByte, 1, 4);
        //前面随机一个字节
        System.arraycopy(new byte[]{(byte) RandomUtils.randomNumner(255)}, 0, unitByte, 0, 1);
        //末尾随机一个字节
        System.arraycopy(new byte[]{(byte) RandomUtils.randomNumner(255)}, 0, unitByte, 5, 1);
        byte[] keys = key.getBytes(utf8);
        for (int i = 0, size = unitByte.length; i < size; i++) {
            for (byte keyBytes0 : keys) {
                unitByte[i] = (byte) (unitByte[i] ^ keyBytes0);
            }
        }
        int bs = 2, bt = 5;
        byte b = unitByte[bs];
        unitByte[bs] = unitByte[bt];
        unitByte[bt] = b;
        return new String(new Base64().encode(unitByte)).replaceAll("\\+", "_").replaceAll("\\/", "-");
    }

    /**
     * 一个int补位再进行位运算解密
     *
     * @param key
     * @param num
     * @return
     * @throws Exception
     */
    public static int intBitDecode(String key, String num) throws Exception {
        num = num.replaceAll("\\_", "+").replaceAll("\\-", "/");
        byte[] source = num.getBytes(utf8);
        source = new Base64().decode(source);
        int bs = 2, bt = 5;
        byte b = source[bt];
        source[bt] = source[bs];
        source[bs] = b;
        //截取int的四个字节
        byte[] intByte = new byte[4];
        //012345,截取 1-4
        System.arraycopy(source, 1, intByte, 0, 4);
        byte[] keys = key.getBytes(utf8);
        byte[] dee = intByte;
        for (int i = 0, size = intByte.length; i < size; i++) {
            for (byte keyBytes0 : keys) {
                intByte[i] = (byte) (dee[i] ^ keyBytes0);
            }
        }
        return ByteBufferUtils.toInt(intByte);
    }

    public static void main(String[] args) throws Exception {
        //		String text = "FE)OI(&MN<CZ*PKXL";
        //		//System.out.println(md5(text));
        //		//System.out.println(md5Safe(text));
        //		//System.out.println(sha256(text));
        //		String signUrl = signUrlData("wd=timestamp&rsv_spt=1&rsv_iqid=0xf9cd128200056b62&issp=1&f=3&rsv_bp=1&rsv_idx=2&ie=utf-8&rqlang=cn&tn=baiduhome_pg&rsv_enter=1&rsv_t=f916JMwU1Du6DvErfUSDjgwjyNzu34nQAqVaSYwoMve7k2lAQpxfSoUE7CQe9EQxAVIO&oq=sign&inputT=125951&rsv_pq=96e6a5240004a06f&rsv_sug3=9&rsv_sug1=9&rsv_sug7=100&rsv_sug2=1&prefixsug=times&rsp=1&rsv_sug4=125951",text);
        //		System.out.println(signUrl);
        //		Map<String,String[]> parameterMap = new HashMap<String,String[]>();
        //		String[] items = StringUtils.split(signUrl,"&");
        //		for(String item:items){
        //			if(item==null || item.trim().length()==0)continue;
        //			String[] kv = item.split("=",2);
        //			if(kv==null || kv.length<1)continue;
        //			String pk = kv[0];
        //			String pv = kv[1]==null?"":kv[1];
        //			parameterMap.put(pk,new String[]{pv});
        //		}
        //		System.out.println(validUrlsign(parameterMap,text));
        //		String ma = bitEncode(text,"t=1524668106919&owner=lujun&userName=lujun");
        //		System.out.println(ma);
        //		String mw = bitDecode(text,ma);
        //		System.out.println(mw);
        //		for(int i=0;i<20;i++){
        //			String mm = intBitEncode(text,i);
        //			int mw = intBitDecode(text,mm);
        //			if(mm.contains("=")){
        //				System.out.println(mw+">>>"+mm);
        //				break;
        //			}
        //		}
    }
}