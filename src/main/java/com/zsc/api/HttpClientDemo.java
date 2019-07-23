package com.zsc.api;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.InputStream;

public class HttpClientDemo {


    public static void main(String[] args) throws Exception {
        httpClient5();
    }

    /**
     * 初步使用
     */
    public static void httpClient1() throws Exception {
        //CloseableHttpClient可关闭的httpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://blog.csdn.net/qq_35246620/article/details/63253518");
        CloseableHttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity(); //返回实体
        String s = EntityUtils.toString(entity, "UTF-8");
        System.out.println(s);
        response.close();
        //客户端关闭
        httpClient.close();
    }

    /**
     * 模拟浏览器
     */
    public static void httpClient2() throws Exception {
        //CloseableHttpClient可关闭的httpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("api://www.tuicool.com/");
        //可以设置：手机/火狐（推荐）/谷歌/IE
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36");
        CloseableHttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity(); //返回实体
        String s = EntityUtils.toString(entity, "UTF-8");
        System.out.println(s);
        response.close();
        //客户端关闭
        httpClient.close();
    }

    /**
     * 状态/类型：Content-Type
     * 200正常 403拒绝   500服务器错误    400未找到页面
     */
    public static void httpClient3() throws Exception {
        //CloseableHttpClient可关闭的httpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("api://www.tuicool.com/");
        //可以设置：手机/火狐（推荐）/谷歌/IE
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36");
        CloseableHttpResponse response = httpClient.execute(httpGet);
        StatusLine statusLine = response.getStatusLine();
        System.out.println("statusLine==" + statusLine);
        int statusCode = statusLine.getStatusCode();
        System.out.println("statusCode==" + statusCode);

        HttpEntity entity = response.getEntity(); //返回实体
        String name = entity.getContentType().getName();
        String value = entity.getContentType().getValue();
        System.out.println(name);   //结果：Content-Type
        System.out.println(value);  //结果：text/html; charset=utf-8
        String s = EntityUtils.toString(entity, "UTF-8");
        System.out.println(s);
        response.close();
        //客户端关闭
        httpClient.close();
    }


    /**
     * 直接请求图片地址，保存图片到本地
     */
    public static void httpClient4() throws Exception {
        //CloseableHttpClient可关闭的httpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("api://pic25.nipic.com/20121205/10197997_003647426000_2.jpg");
        //可以设置：手机/火狐（推荐）/谷歌/IE
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36");
        CloseableHttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity(); //返回实体
        if (entity == null) {
            return;
        }
        String value = entity.getContentType().getValue();
        System.out.println(value);
        //图片只需要判断 image/jpeg的image
        if (!value.startsWith("image")) {
            return;
        }
        InputStream inputStream = entity.getContent();
        //使用缓冲流保存到本地
        // TODO: 2019/7/16  生成唯一标识  获取后缀
        File file = new File("E://image/first.jpg");
        FileUtils.copyToFile(inputStream, file);

        //   String s = EntityUtils.toString(entity, "UTF-8");
        //    System.out.println(s);
        response.close();
        //客户端关闭
        httpClient.close();
    }

    /**
     * 代理ip
     * 透明代理 匿名代理    混淆代理    高匿代理（推荐，最好选择，前三个都能被发现）
     * 高匿代理地址ip：https://www.xicidaili.com/?_id=015448211445
     */

    public static void httpClient5() throws Exception {
        //CloseableHttpClient可关闭的httpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("api://www.tuicool.com/");
        //可以设置：手机/火狐（推荐）/谷歌/IE
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36");
        HttpHost proxy = new HttpHost("58.253.159.116", 9999);
        //RequestConfig:获取和配置一些外部的网络环境
        RequestConfig requestConfig = RequestConfig.custom().setProxy(proxy).build();
        httpGet.setConfig(requestConfig);

        CloseableHttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity(); //返回实体
        if (entity == null) {
            return;
        }


    }

    /**
     * 连接超时/读取超时
     *
     * @throws Exception
     */
    public static void httpClient6() throws Exception {
        //CloseableHttpClient可关闭的httpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("api://www.tuicool.com/");
        //可以设置：手机/火狐（推荐）/谷歌/IE
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36");
        // HttpHost proxy = new HttpHost("58.253.159.116", 9999);
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(1000)//从连接池中获取连接的超时时间
                .setConnectTimeout(1000)    //连接超时
                .setSocketTimeout(1000)     //读取超时

                .build();

        httpGet.setConfig(requestConfig);

        CloseableHttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity(); //返回实体
        if (entity == null) {
            return;
        }
    }

    /**
     * httpPost请求
     *
     * @param jsonString
     * @throws Exception
     */
    public static void httpClient7(String jsonString) throws Exception {
        //CloseableHttpClient可关闭的httpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("api://www.tuicool.com/");
        //可以设置：手机/火狐（推荐）/谷歌/IE
        httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36");
        StringEntity stringEntity = new StringEntity(jsonString);
        stringEntity.setContentType("application/json");
        stringEntity.setContentEncoding("UTF-8");
        httpPost.setEntity(stringEntity);
        // HttpHost proxy = new HttpHost("58.253.159.116", 9999);
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(1000)//从连接池中获取连接的超时时间
                .setConnectTimeout(1000)    //连接超时
                .setSocketTimeout(1000)     //读取超时
                .build();

        httpPost.setConfig(requestConfig);
        CloseableHttpResponse response = httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity(); //返回实体
        if (entity == null) {
            return;
        }
    }
}
