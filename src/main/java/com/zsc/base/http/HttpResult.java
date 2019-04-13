package com.zsc.base.http;
import com.alibaba.fastjson.JSONObject;
import com.zsc.base.utils.JsonUtils;
import com.zsc.base.utils.LogUtils;
import com.zsc.base.utils.StringUtils;

import org.apache.http.Header;
import org.apache.http.client.CookieStore;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class HttpResult {
	private int statusCode;
	private String message;
	private byte[] buffer;
	private boolean isGzip;
	private String html;
	private CookieStore cookies;
	private List<Header> headers;
	private String requestUrl;
	private String responseUrl;
	private boolean success;
	private LoginAuth auth;
	public HttpResult() {
		this.headers = new ArrayList<Header>();
		this.html = "";
	}
	public CookieStore getCookies() {
		return cookies;
	}
	public void setCookies(CookieStore cookies) {
		this.cookies = cookies;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public String getStatusCodeStr() {
		return String.valueOf(statusCode);
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	public void addHeader(Header header) {
		if (header != null && this.headers.contains(header) == false) {
			this.headers.add(header);
		}
	}
	public List<Header> getHeaders() {
		return this.headers;
	}
	public void setRequestUrl(String requestUrl){
		this.requestUrl = requestUrl;
	}
	public String getRequestUrl(){
		return requestUrl;
	}
	public String getResponseUrl() {
		return responseUrl;
	}
	public void setResponseUrl(String responseUrl) {
		this.responseUrl = responseUrl;
	}
	public byte[] getBuffer() {
		return buffer;
	}
	public void setBuffer(byte[] buffer) {
		this.buffer = buffer;
	}
	public String makeHtml(String encoding) {
		return makeHtml("", encoding);
	}
	public String makeHtml(String errorInfo, String encoding) {
		try {
			if (this.buffer != null && this.buffer.length > 0) {
				this.html = new String(this.buffer, encoding);
				if (this.html != null) {
					this.html = this.html.trim();
				} else {
					this.html = "";
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			this.html = "";
		}
		if(this.statusCode==200){
			setSuccess(true);
		}
		return this.html;
	}
//	public Document asXML(){
//		try{
//			return Jsoup.parse(this.html,"",new Parser(new XmlTreeBuilder()));
//		}catch (Exception e) {
//			return null;
//		}
//	}
//	public Document asHTML(){
//		try{
//			return Jsoup.parse(this.html);
//		}catch (Exception e) {
//			return null;
//		}
//	}
	public JSONObject asJSON(){
		try{
			return JsonUtils.parseString(this.html);
		}catch (Exception e) {
			//e.printStackTrace();
			LogUtils.error(this,"JSON转换格式错误");
			return null;
		}
	}
	public void setGzip(boolean isGzip) {
		this.isGzip = isGzip;
	}
	public boolean isGzip() {
		return isGzip;
	}
	public void setSuccess(boolean success){
		this.success = success;
	}
	public boolean isSuccess(){
		return success && StringUtils.isNotEmpty(this.html);
	}
	public void setAuth(LoginAuth auth){
		this.auth = auth;
	}
	public LoginAuth getAuth(){
		return auth;
	}
	public void setMessage(String message){
		this.message = message;
	}
	public String getMessage(){
		return message;
	}
}
