package com.zsc.base.http;
import org.apache.http.client.CookieStore;
public class LoginAuth {
	public enum LOGIN_STATES {
		SUECCESS, FAILURE, NOTFIND, EXCEPTION
	}
	final private long EXCEED_TIME = 5L * 60L * 1000L;// 5分钟过期时间
	protected String domain;
	protected int authType;
	protected boolean failure;
	protected long expiresTime;
	protected CookieStore cookie;
	protected LOGIN_STATES states;
	public LoginAuth(String domain) {
		this.domain = domain;
		this.expiresTime = System.currentTimeMillis() + EXCEED_TIME;
	}
	public int getAuthType() {
		return authType;
	}
	public void setAuthType(int authType) {
		this.authType = authType;
	}
	public boolean isFailure() {
		if (EXCEED_TIME > 0) {
			if (System.currentTimeMillis() > expiresTime) {
				failure = false;
			}
		}
		return failure;
	}
	public void setFailure(boolean failure) {
		this.failure = failure;
	}
	public CookieStore getCookie() {
		if (this.cookie != null) {
			HttpUtils.printCookies(this.cookie);
		}
		return cookie;
	}
	public void setCookie(CookieStore cookie) {
		this.cookie = cookie;
	}
	public long getExpiresTime() {
		return expiresTime;
	}
	public void setExpiresTime(long expiresTime) {
		this.expiresTime = expiresTime;
	}
	public String getDomain() {
		return domain;
	}
	public void setStates(LOGIN_STATES states) {
		if (states != LOGIN_STATES.SUECCESS) {
			this.setFailure(true);
		}
		this.states = states;
	}
}
