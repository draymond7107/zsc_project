package com.zsc.base.http;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import java.util.ArrayList;
import java.util.List;

public class CommonHeads {
	private static List<Header> heads = new ArrayList<Header>();
	static{
		heads.add(new BasicHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"));
		heads.add(new BasicHeader("Accept-Charset","GBK,utf-8;q=0.7,*;q=0.3"));
		heads.add(new BasicHeader("Accept-Language","zh-CN,zh;q=0.8"));
		heads.add(new BasicHeader("Cache-Control","max-age=0"));
		heads.add(new BasicHeader("Proxy-Connection","keep-alive"));
		heads.add(new BasicHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.17 (KHTML, like Gecko) Chrome/24.0.1312.56 Safari/537.17"));
	}
	
	public static List<Header> getCommonHeads(){
		List<Header> newList = new ArrayList<Header>();
		newList.addAll(heads);
		return newList;
	}
}
