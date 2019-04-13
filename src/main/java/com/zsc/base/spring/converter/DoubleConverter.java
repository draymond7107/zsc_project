package com.zsc.base.spring.converter;
import org.springframework.core.convert.converter.Converter;

/**
 * 字符串转int型
 * 功能说明：<br>
 * 模块名称：NRBase<br>
 * 功能描述：DoubleConverter<br>
 * 文件名称: DoubleConverter.java<br>
 * 系统名称：ICELOVE<br>
 * 软件著作权：ICELOVE 版权所有<br>
 * 开发人员：lujun <br>
 * 开发时间：2016-9-3 下午12:55:29<br>
 * 系统版本：1.0.0<br>
 */
public class DoubleConverter implements Converter<String,Double>{
	@Override
	public Double convert(String arg0){
		if(arg0==null || arg0.length()==0)return null;
		try {
			return Double.parseDouble(arg0);
		} catch (Exception ex) {
			return null;
		}
	}
}
