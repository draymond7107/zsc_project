package com.zsc.base.spring.converter;
import org.springframework.core.convert.converter.Converter;

/**
 * 字符串转int型 默认值是0
 * 功能说明：<br>
 * 模块名称：NRBase<br>
 * 功能描述：IntConverter<br>
 * 文件名称: IntConverter.java<br>
 * 系统名称：ICELOVE<br>
 * 软件著作权：ICELOVE 版权所有<br>
 * 开发人员：lujun <br>
 * 开发时间：2016-9-3 下午12:55:29<br>
 * 系统版本：1.0.0<br>
 */
public class IntConverter implements Converter<String,Integer>{
	@Override
	public Integer convert(String arg0){
		if(arg0==null || arg0.length()==0)return null;
		try {
			return Integer.parseInt(arg0);
		} catch (Exception ex) {
			return null;
		}
	}
}
