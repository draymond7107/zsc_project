package com.zsc.base.http;
import com.alibaba.fastjson.JSONObject;
import com.zsc.base.Config;
import com.zsc.base.utils.CryptUtils;
import com.zsc.base.utils.StringUtils;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

/**
 * 功能说明：<br>
 * 模块名称：MyIceLove<br>
 * 功能描述：HttpUtils<br>
 * 文件名称: HttpUtils.java<br>
 * 系统名称：ICELOVE<br>
 * 软件著作权：ICELOVE 版权所有<br>
 * 开发人员：lujun <br>
 * 开发时间：2017-1-15 下午9:50:10<br>
 * 系统版本：1.0.0<br>
 */
public class HttpUtils {
    private static Pattern pattern = Pattern.compile("<meta[^>]*content=(['\"])?[^>]*charset=((gb2312)|(gbk)|(utf-8))\\1[^>]*>", Pattern.CASE_INSENSITIVE);
    private static Pattern charsetPattern = Pattern.compile("charset=((gb2312)|(gbk)|(utf-8))", Pattern.CASE_INSENSITIVE);

    public static HttpResult get(String url) throws Exception {
        return HttpUtils.get(url, Config.ENC_UTF, 90, null, null, null);
    }

    public static HttpResult get(String url, String encoding) throws Exception {
        return HttpUtils.get(url, encoding, 90, null, null, null);
    }

    public static HttpResult get(String url, String encoding, String localAddress) throws Exception {
        return HttpUtils.get(url, encoding, 90, null, null, localAddress);
    }

    public static HttpResult get(String url, String encoding, int timeOut) throws Exception {
        return HttpUtils.get(url, encoding, timeOut, null, null, null);
    }

    public static HttpResult get(String url, String encoding, int timeOut, String localAddress) throws Exception {
        return HttpUtils.get(url, encoding, timeOut, null, null, localAddress);
    }

    public static HttpResult get(String url, String encoding, CookieStore cookies) throws Exception {
        return HttpUtils.get(url, encoding, 90, cookies, null, null);
    }

    public static HttpResult get(String url, String encoding, CookieStore cookies, String localAddress) throws Exception {
        return HttpUtils.get(url, encoding, 90, cookies, null, localAddress);
    }

    public static HttpResult get(String url, String encoding, int timeOut, CookieStore cookies) throws Exception {
        return HttpUtils.get(url, encoding, timeOut, cookies, null, null);
    }

    public static HttpResult get(String url, String encoding, int timeOut, CookieStore cookies, String localAddress) throws Exception {
        return HttpUtils.get(url, encoding, timeOut, cookies, null, localAddress);
    }

    public static HttpResult get(String url, List<Header> headers) throws Exception {
        return HttpUtils.get(url, Config.ENC_UTF, 90, null, headers, null);
    }

    public static HttpResult get(String url, CookieStore cookies, List<Header> headers) throws Exception {
        return HttpUtils.get(url, Config.ENC_UTF, 90, cookies, headers, null);
    }

    public static HttpResult get(String url, String encoding, List<Header> headers) throws Exception {
        return HttpUtils.get(url, encoding, 90, null, headers, null);
    }

    public static HttpResult get(String url, String encoding, List<Header> headers, String localAddress) throws Exception {
        return HttpUtils.get(url, encoding, 90, null, headers, localAddress);
    }

    public static HttpResult get(String url, String encoding, int timeOut, List<Header> headers) throws Exception {
        return HttpUtils.get(url, encoding, timeOut, null, headers, null);
    }

    public static HttpResult get(String url, String encoding, int timeOut, List<Header> headers, String localAddress) throws Exception {
        return HttpUtils.get(url, encoding, timeOut, null, headers, localAddress);
    }

    public static HttpResult get(String url, String encoding, CookieStore cookies, List<Header> headers) throws Exception {
        return HttpUtils.get(url, encoding, 90, cookies, headers, null);
    }

    public static HttpResult get(String url, String encoding, CookieStore cookies, List<Header> headers, String localAddress) throws Exception {
        return HttpUtils.get(url, encoding, 90, cookies, headers, localAddress);
    }

    public static HttpResult get(String url, String encoding, int timeOut, CookieStore cookies, List<Header> headers) throws Exception {
        return HttpUtils.get(url, encoding, timeOut, cookies, headers, null);
    }

    public static HttpResult get(String url, String encoding, int timeOut, CookieStore cookies, List<Header> headers, String localAddress) {
        HttpClientBuilder clientBuilder = null;
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpGet httpGet = null;
        HttpResult httpResult = new HttpResult();
        try {
            clientBuilder = HttpClients.custom();
            httpGet = new HttpGet(url);
            if (cookies != null) {
                clientBuilder.setDefaultCookieStore(cookies);
            }
            if (url.startsWith("https")) {
                //忽略所有的SSL证书验证
                HostnameVerifier hostnameVerifier = new HostnameVerifier() {
                    public boolean verify(String urlHostName, SSLSession session) {
                        return true;
                    }
                };
                SSLContext sslcontext = SslUtils.createIgnoreVerifySSL();
                SSLConnectionSocketFactory sslcs = new SSLConnectionSocketFactory(sslcontext, hostnameVerifier);
                Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create().register("http", PlainConnectionSocketFactory.INSTANCE).register("https", sslcs).build();
                PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
                clientBuilder.setConnectionManager(connManager);
            }
            httpClient = clientBuilder.build();
            if (headers != null && !headers.isEmpty()) {
                Header[] headerArray = new Header[headers.size()];
                headers.toArray(headerArray);
                httpGet.setHeaders(headerArray);
            }
            RequestConfig.Builder configBuilder = RequestConfig.custom();
            if (StringUtils.isNotEmpty(localAddress)) {
                InetAddress inetAddress = InetAddress.getByName(localAddress);
                configBuilder.setLocalAddress(inetAddress);
            }
            RequestConfig requestConfig = configBuilder.setSocketTimeout(timeOut * 1000).setConnectTimeout(timeOut * 1000).setConnectionRequestTimeout(timeOut * 1000).build();
            httpGet.setConfig(requestConfig);
            response = httpClient.execute(httpGet);
            httpResult = readResponse(response, encoding, cookies);
            httpResult.setCookies(cookies);
            httpResult.setRequestUrl(url);
        } catch (ConnectTimeoutException e) {
            httpResult.setSuccess(false);
            httpResult.setStatusCode(-1);
            httpResult.setMessage("请求超时:" + url);
        } catch (Exception e) {
            httpResult.setSuccess(false);
            httpResult.setStatusCode(-1);
            httpResult.setMessage("请求异常:" + url);
        } finally {
            try {
                // 关闭连接,释放资源
                if (response != null) {
                    response.close();
                    response = null;
                }
                if (httpClient != null) {
                    httpClient.close();
                    httpClient = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return httpResult;
    }

    private static HttpResult readResponse(HttpResponse response, String encoding, CookieStore cookieStore) throws Exception {
        HttpResult httpResult = new HttpResult();
        //HttpHost target = (HttpHost)localContext.getAttribute(ExecutionContext.HTTP_TARGET_HOST);
        //HttpUriRequest req = (HttpUriRequest)localContext.getAttribute(ExecutionContext.HTTP_REQUEST);
        //httpResult.setResponseUrl(target.toString() + req.getURI().toString());
        httpResult.setStatusCode(response.getStatusLine().getStatusCode());
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            Header ceheader = entity.getContentEncoding();
            if (ceheader != null) {
                for (HeaderElement element : ceheader.getElements()) {
                    if (element.getName().equalsIgnoreCase("gzip")) {
                        httpResult.setGzip(true);
                    }
                }
            }
            if (httpResult.isGzip()) {
                GzipDecompressingEntity gzipEntity = new GzipDecompressingEntity(entity);
                httpResult.setBuffer(HttpUtils.entityToByte(gzipEntity));
                if (StringUtils.isNotEmpty(encoding)) {
                    httpResult.setHtml(httpResult.makeHtml(encoding));
                }
            } else {
                httpResult.setBuffer(HttpUtils.entityToByte(entity));
                if (encoding != null && (StringUtils.isNotEmpty(encoding))) {
                    httpResult.makeHtml(encoding);
                }
            }
            EntityUtils.consume(entity);
        }
        return httpResult;
    }

    //将map型转为请求参数型
    public static String urlEncode(Map<String, Object> data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static String entityToStringAutoCharset(HttpEntity entity, String contentType) throws Exception {
        InputStream is = entity.getContent();
        if (is == null) {
            System.err.println("is == null");
            return "";
        }
        String ret = "";
        byte[] buffer = new byte[0];
        byte[] temp = new byte[1024];
        int count = is.read(temp);
        while (count > 0) {
            int length = buffer.length;
            buffer = Arrays.copyOf(buffer, length + count);
            System.arraycopy(temp, 0, buffer, length, count);
            count = is.read(temp);
        }
        StringBuilder builder = new StringBuilder();
        String charset = String.valueOf("");
        for (int i = 0; i < buffer.length && ("".equals(charset) == true); i++) {
            char c = (char) buffer[i];
            switch (c) {
                case '<':
                    builder.delete(0, builder.length());
                    builder.append(c);
                    break;
                case '>':
                    if (builder.length() > 0) {
                        builder.append(c);
                    }
                    String meta = builder.toString();
                    if (meta.toLowerCase().startsWith("<meta") == true) {
                        charset = getCharsetFromMeta(meta);
                    }
                    break;
                default:
                    if (builder.length() > 0) {
                        builder.append(c);
                    }
                    break;
            }
        }
        if ("".equals(charset) == false) {
            ret = new String(buffer, charset);
        } else {
            Matcher m = charsetPattern.matcher(contentType);
            if (m.find() == true) {
                charset = m.group(1);
            } else {
                charset = "UTF-8";
            }
            ret = new String(buffer, charset);
        }
        return ret;
    }

    private static String getCharsetFromMeta(String meta) {
        if (meta == null || "".equals(meta) == true) {
            return "";
        }
        Matcher m = pattern.matcher(meta);
        if (m.find() == true) {
            return m.group(2);
        }
        return "";
    }

    public static HttpResult post(String url, String data, String encoding) throws Exception {
        return HttpUtils.post(url, data, encoding, 90, null, null, null);
    }

    public static HttpResult post(String url, String data, String encoding, List<Header> headers) throws Exception {
        return HttpUtils.post(url, data, encoding, 90, null, headers, null);
    }

    public static HttpResult post(String url, String data, String encoding, String localAddress) throws Exception {
        return HttpUtils.post(url, data, encoding, 90, null, null, localAddress);
    }

    public static HttpResult post(String url, String data, String encoding, int timeOut) throws Exception {
        return HttpUtils.post(url, data, encoding, timeOut, null, null, null);
    }

    public static HttpResult post(String url, String data, String encoding, int timeOut, String localAddress) throws Exception {
        return HttpUtils.post(url, data, encoding, timeOut, null, null, localAddress);
    }

    public static HttpResult post(String url, String data, String encoding, CookieStore cookies) throws Exception {
        return HttpUtils.post(url, data, encoding, 90, cookies, null, null);
    }

    public static HttpResult post(String url, String data, String encoding, CookieStore cookies, String localAddress) throws Exception {
        return HttpUtils.post(url, data, encoding, 90, cookies, null, localAddress);
    }

    public static HttpResult post(String url, String data, String encoding, int timeOut, CookieStore cookies) throws Exception {
        return HttpUtils.post(url, data, encoding, timeOut, cookies, null, null);
    }

    public static HttpResult post(String url, String data, String encoding, int timeOut, CookieStore cookies, String localAddress) throws Exception {
        return HttpUtils.post(url, data, encoding, timeOut, cookies, null, localAddress);
    }

    public static HttpResult post(String url, String data, String encoding, int timeOut, List<Header> headers) throws Exception {
        return HttpUtils.post(url, data, encoding, timeOut, null, headers, null);
    }

    public static HttpResult post(String url, String data, String encoding, int timeOut, List<Header> headers, String localAddress) throws Exception {
        return HttpUtils.post(url, data, encoding, timeOut, null, headers, localAddress);
    }

    public static HttpResult post(String url, String data, String encoding, CookieStore cookies, List<Header> headers) throws Exception {
        return HttpUtils.post(url, data, encoding, 90, cookies, headers, null);
    }

    public static HttpResult post(String url, String data, String encoding, CookieStore cookies, List<Header> headers, String localAddress) throws Exception {
        return HttpUtils.post(url, data, encoding, 90, cookies, headers, localAddress);
    }

    public static HttpResult post(String url, String data, String encoding, int timeOut, CookieStore cookies, List<Header> headers) throws Exception {
        return HttpUtils.post(url, data, encoding, timeOut, cookies, headers, null);
    }

    public static HttpResult post(String url, String data, String encoding, int timeOut, CookieStore cookies, List<Header> headers, String localAddress) throws Exception {
        HttpClientBuilder clientBuilder = null;
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpPost httpPost = null;
        HttpResult httpResult = new HttpResult();
        try {
            clientBuilder = HttpClients.custom();
            httpPost = new HttpPost(url);
            if (cookies != null) {
                clientBuilder.setDefaultCookieStore(cookies);
            }
            httpClient = clientBuilder.build();
            if (headers != null && !headers.isEmpty()) {
                Header[] headerArray = new Header[headers.size()];
                headers.toArray(headerArray);
                httpPost.setHeaders(headerArray);
            }
            RequestConfig.Builder configBuilder = RequestConfig.custom();
            if (StringUtils.isNotEmpty(localAddress)) {
                InetAddress inetAddress = InetAddress.getByName(localAddress);
                configBuilder.setLocalAddress(inetAddress);
            }
            RequestConfig requestConfig = configBuilder.setSocketTimeout(timeOut * 1000).setConnectTimeout(timeOut * 1000).setConnectionRequestTimeout(timeOut * 1000).build();
            httpPost.setConfig(requestConfig);
            try {
                StringEntity stringEntity = new StringEntity(data, encoding);
                stringEntity.setContentType("application/x-www-form-urlencoded");
                httpPost.setEntity(stringEntity);
            } catch (Exception e) {
                throw e;
            }
            response = httpClient.execute(httpPost);
            httpResult = readResponse(response, encoding, cookies);
            httpResult.setCookies(cookies);
            httpResult.setRequestUrl(url);
        } catch (Exception e) {
            e.printStackTrace();
            httpResult.setSuccess(false);
            httpResult.setStatusCode(-1);
        } finally {
            try {
                // 关闭连接,释放资源
                if (response != null) {
                    response.close();
                    response = null;
                }
                if (httpClient != null) {
                    httpClient.close();
                    httpClient = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return httpResult;
    }

    public static HttpResult postJSON(String url, JSONObject data,List<Header> headers) throws Exception {
        int timeOut = 90;
        String encoding = "UTF-8";
        HttpClientBuilder clientBuilder = null;
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpPost httpPost = null;
        HttpResult httpResult = new HttpResult();
        try {
            clientBuilder = HttpClients.custom();
            httpPost = new HttpPost(url);
            httpClient = clientBuilder.build();
            if (headers != null && !headers.isEmpty()) {
                Header[] headerArray = new Header[headers.size()];
                headers.toArray(headerArray);
                httpPost.setHeaders(headerArray);
            }
            RequestConfig.Builder configBuilder = RequestConfig.custom();
            RequestConfig requestConfig = configBuilder.setSocketTimeout(timeOut * 1000).setConnectTimeout(timeOut * 1000).setConnectionRequestTimeout(timeOut * 1000).build();
            httpPost.setConfig(requestConfig);
            try {
                StringEntity stringEntity = new StringEntity(data.toJSONString(), encoding);
                stringEntity.setContentType("application/json");
                httpPost.setEntity(stringEntity);
            } catch (Exception e) {
                throw e;
            }
            response = httpClient.execute(httpPost);
            httpResult = readResponse(response, encoding,null);
            httpResult.setCookies(null);
            httpResult.setRequestUrl(url);
        } catch (Exception e) {
            e.printStackTrace();
            httpResult.setSuccess(false);
            httpResult.setStatusCode(-1);
        } finally {
            try {
                // 关闭连接,释放资源
                if (response != null) {
                    response.close();
                    response = null;
                }
                if (httpClient != null) {
                    httpClient.close();
                    httpClient = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return httpResult;
    }

    /**
     * @param url
     * @param fileName
     * @param file
     * @param nameValues
     * @param encoding
     * @param timeOut
     * @param cookies
     * @param headers
     * @param localAddress
     * @return
     */
    public static HttpResult postFile(String url, String fileName, File file, List<NameValuePair> nameValues, String encoding, int timeOut, CookieStore cookies, List<Header> headers, String localAddress) {
        HttpClientBuilder clientBuilder = null;
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpPost httpPost = null;
        HttpResult httpResult = new HttpResult();
        try {
            clientBuilder = HttpClients.custom();
            httpPost = new HttpPost(url);
            if (cookies != null) {
                clientBuilder.setDefaultCookieStore(cookies);
            }
            httpClient = clientBuilder.build();
            if (headers != null && !headers.isEmpty()) {
                Header[] headerArray = new Header[headers.size()];
                headers.toArray(headerArray);
                httpPost.setHeaders(headerArray);
            }
            RequestConfig.Builder configBuilder = RequestConfig.custom();
            if (StringUtils.isNotEmpty(localAddress)) {
                InetAddress inetAddress = InetAddress.getByName(localAddress);
                configBuilder.setLocalAddress(inetAddress);
            }
            RequestConfig requestConfig = configBuilder.setSocketTimeout(timeOut * 1000).setConnectTimeout(timeOut * 1000).setConnectionRequestTimeout(timeOut * 1000).build();
            httpPost.setConfig(requestConfig);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.addBinaryBody(fileName, file, ContentType.DEFAULT_BINARY, file.getName());
            for (NameValuePair nameValue : nameValues) {
                builder.addTextBody(nameValue.getName(), nameValue.getValue(), ContentType.DEFAULT_BINARY);
            }
            HttpEntity entity = builder.build();
            httpPost.setEntity(entity);
            response = httpClient.execute(httpPost);
            httpResult = readResponse(response, encoding, cookies);
            httpResult.setCookies(cookies);
            httpResult.setRequestUrl(url);
        } catch (Exception e) {
            e.printStackTrace();
            httpResult.setSuccess(false);
            httpResult.setStatusCode(-1);
        } finally {
            try {
                // 关闭连接,释放资源
                if (response != null) {
                    response.close();
                    response = null;
                }
                if (httpClient != null) {
                    httpClient.close();
                    httpClient = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return httpResult;
    }

    public static HttpResult post(String url, String data, String encoding, int timeOut, CookieStore cookies, List<Header> headers, String localAddress, String filePath, String keyPassword) throws Exception {
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        FileInputStream instream = new FileInputStream(new File(filePath));
        try {
            keyStore.load(instream, keyPassword.toCharArray());
        } finally {
            instream.close();
        }
        SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, keyPassword.toCharArray()).build();
        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslcontext, new String[]{"TLSv1"}, null, SSLConnectionSocketFactory.getDefaultHostnameVerifier());
        CloseableHttpClient httpClient = null;
        HttpClientBuilder clientBuilder = null;
        CloseableHttpResponse response = null;
        HttpPost httpPost = null;
        HttpResult httpResult = new HttpResult();
        try {
            clientBuilder = HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory);//HttpClients.custom();
            httpPost = new HttpPost(url);
            if (cookies != null) {
                clientBuilder.setDefaultCookieStore(cookies);
            }
            httpClient = clientBuilder.build();
            if (headers != null && !headers.isEmpty()) {
                Header[] headerArray = new Header[headers.size()];
                headers.toArray(headerArray);
                httpPost.setHeaders(headerArray);
            }
            RequestConfig.Builder configBuilder = RequestConfig.custom();
            if (StringUtils.isNotEmpty(localAddress)) {
                InetAddress inetAddress = InetAddress.getByName(localAddress);
                configBuilder.setLocalAddress(inetAddress);
            }
            RequestConfig requestConfig = configBuilder.setSocketTimeout(timeOut * 1000).setConnectTimeout(timeOut * 1000).setConnectionRequestTimeout(timeOut * 1000).build();
            httpPost.setConfig(requestConfig);
            try {
                StringEntity stringEntity = new StringEntity(data, encoding);
                stringEntity.setContentType("application/x-www-form-urlencoded");
                httpPost.setEntity(stringEntity);
            } catch (Exception e) {
                throw e;
            }
            response = httpClient.execute(httpPost);
            httpResult = readResponse(response, encoding, cookies);
            httpResult.setCookies(cookies);
            httpResult.setRequestUrl(url);
        } catch (Exception e) {
            e.printStackTrace();
            httpResult.setSuccess(false);
            httpResult.setStatusCode(-1);
        } finally {
            try {
                // 关闭连接,释放资源
                if (response != null) {
                    response.close();
                    response = null;
                }
                if (httpClient != null) {
                    httpClient.close();
                    httpClient = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return httpResult;
    }

    /**
     * 执行文件下载
     *
     * @param url      远程下载文件地址
     * @param savePath 本地存储文件地址
     * @param charset  请求编码，默认UTF-8
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static HttpResult downloadFile(String url, String savePath, String charset) {
        CloseableHttpResponse response = null;
        InputStream in = null;
        FileOutputStream fout = null;
        CloseableHttpClient httpClient = null;
        HttpResult httpResult = new HttpResult();
        try {
            httpClient = HttpClients.createDefault();
            HttpGet httpget = new HttpGet(url);
            response = httpClient.execute(httpget);
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                httpResult.setHtml("下载出现异常:entity=null");
                httpResult.setSuccess(false);
                return httpResult;
            }
            Header contentHeader = response.getFirstHeader("Content-Type");
            String contentType = contentHeader != null ? contentHeader.getValue() : null;
            if (contentType != null && contentType.contains("text/html")) {
                httpResult.setSuccess(false);
                httpResult.setStatusCode(response.getStatusLine().getStatusCode());
                String html = EntityUtils.toString(entity);
                httpResult.setHtml(html);
                return httpResult;
            }
            in = entity.getContent();
            File file = new File(savePath);
            fout = new FileOutputStream(file);
            int l = -1;
            byte[] tmp = new byte[1024];
            while ((l = in.read(tmp)) != -1) {
                fout.write(tmp, 0, l);
                //注意这里如果用OutputStream.write(buff)的话，图片会失真
            }
            //将文件输出到本地
            fout.flush();
            EntityUtils.consume(entity);
            httpResult.setSuccess(true);
            httpResult.setHtml(file.getAbsolutePath());
            return httpResult;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭低层流。
            if (fout != null) {
                try {
                    fout.close();
                } catch (Exception e) {
                }
            }
            if (response != null) {
                try {
                    response.close();
                } catch (Exception e) {
                }
            }
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (Exception e) {
                }
            }
            httpClient = null;
            response = null;
            fout = null;
            httpResult.setHtml("程序出现异常");
        }
        httpResult.setSuccess(false);
        return httpResult;
    }

    public static List<NameValuePair> parsePostParams(byte[] data) {
        List<org.apache.http.NameValuePair> params = new ArrayList<NameValuePair>();
        if (data != null && data.length > 0) {
            int ix = 0;
            int ox = 0;
            String key = null;
            String value = null;
            while (ix < data.length) {
                byte c = data[ix++];
                switch ((char) c) {
                    case '&':
                        value = new String(data, 0, ox);
                        if (key != null) {
                            params.add(new BasicNameValuePair(key, value));
                            key = null;
                        }
                        ox = 0;
                        break;
                    case '=':
                        if (key == null) {
                            key = new String(data, 0, ox);
                            ox = 0;
                        } else {
                            data[ox++] = c;
                        }
                        break;
                    default:
                        data[ox++] = c;
                }
            }
            if (key != null) {
                value = new String(data, 0, ox);
                params.add(new BasicNameValuePair(key, value));
            }
        }
        return params;
    }

    public static byte[] entityToByte(HttpEntity entity) throws Exception, SocketTimeoutException {
        return EntityUtils.toByteArray(entity);
    }

    public static List<Header> getBasicRequestHeader(int headerTypes) {
        List<Header> headers = new ArrayList<Header>();
        if ((headerTypes & HttpConstant.ACCEPT) == HttpConstant.ACCEPT) {
            headers.add(new BasicHeader("Accept", "image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, application/x-shockwave-flash, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, application/x-ms-application, application/x-ms-xbap, application/vnd.ms-xpsdocument, application/xaml+xml, */*"));
        }
        if ((headerTypes & HttpConstant.ACCEPT_ENCODING) == HttpConstant.ACCEPT_ENCODING) {
            // headers.add(new BasicHeader("Accept-Encoding", "gzip, deflate"));
        }
        if ((headerTypes & HttpConstant.ACCEPT_LANGUAGE) == HttpConstant.ACCEPT_LANGUAGE) {
            headers.add(new BasicHeader("Accept-Language", "zh-cn"));
        }
        if ((headerTypes & HttpConstant.USER_AGENT) == HttpConstant.USER_AGENT) {
            headers.add(new BasicHeader("User-Agent", UserAgent.DEFAULT));
        }
        return headers;
    }

    public static void printCookies(CookieStore cookies) {
        if (cookies == null) {
            return;
        }
        List<Cookie> lst = cookies.getCookies();
        if (lst.size() == 0) {
        }
    }

    public static String getFullDomain(String url) {
        String temp = "";
        if (url.indexOf("http://") == 0) {
            temp = url.substring(7);
        } else if (url.indexOf("https://") == 0) {
            temp = url.substring(8);
        }
        if (temp.indexOf("/") > -1) {
            temp = temp.substring(0, temp.indexOf("/"));
        }
        return temp;
    }

    public static String buildUrl(String host, String path, Map<String, String> querys) throws UnsupportedEncodingException {
        StringBuilder sbUrl = new StringBuilder();
        sbUrl.append(host);
        if (!StringUtils.isBlank(path)) {
            sbUrl.append(path);
        }
        if (null != querys) {
            StringBuilder data = new StringBuilder();
            for (Map.Entry<String, String> query : querys.entrySet()) {
                if (data.length() > 0) {
                    data.append("&");
                }
                String value = query.getValue();
                if (StringUtils.isBlank(query.getKey()) && !StringUtils.isBlank(value)) {
                    data.append(query.getValue());
                }
                if (!StringUtils.isBlank(query.getKey())) {
                    data.append(query.getKey());
                    if (!StringUtils.isBlank(value)) {
                        data.append("=");
                        data.append(URLEncoder.encode(value, "utf-8"));
                    }
                }
            }
            if (data.length() > 0) {
                sbUrl.append("?").append(data);
            }
        }
        return sbUrl.toString();
    }

    /**
     * 创建post的data对象
     *
     * @param querys
     * @param encode
     * @return
     */
    public static String buildPostData(Map<String, String> querys, boolean encode) {
        StringBuilder data = new StringBuilder();
        if (null != querys) {
            for (Map.Entry<String, String> query : querys.entrySet()) {
                if (data.length() > 0) {
                    data.append("&");
                }
                String value = query.getValue();
                if (StringUtils.isBlank(query.getKey()) && !StringUtils.isBlank(value)) {
                    data.append(query.getValue());
                }
                if (StringUtils.isNotBlank(query.getKey())) {
                    data.append(query.getKey());
                    if (StringUtils.isNotBlank(value)) {
                        data.append("=");
                        data.append(encode ? CryptUtils.urlEncode(value) : value);
                    }
                }
            }
        }
        return data.toString();
    }
}

class HttpConstant {
    public static final int ACCEPT = 0x01;
    public static final int ACCEPT_ENCODING = 0x02;
    public static final int ACCEPT_LANGUAGE = 0x04;
    public static final int USER_AGENT = 0x08;
    public static final int ALL = (ACCEPT | ACCEPT_ENCODING | ACCEPT_LANGUAGE | USER_AGENT);
}
