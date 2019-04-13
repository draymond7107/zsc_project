package com.zsc.base.spring.converter;
import com.zsc.base.utils.DateUtils;

import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class DateConverter implements Converter<String,Date>{
	private static final List<String> formarts = new ArrayList<String>(4);
	static{
		formarts.add("yyyy-MM");
		formarts.add("yyyy-MM-dd");
		formarts.add("yyyy-MM-dd HH:mm");
		formarts.add("yyyy-MM-dd HH:mm:ss");
		formarts.add("HH:mm:ss");
	}
	@Override
	public Date convert(String source){
		if(source==null || source.trim().length()==0)return null;
		String value = source.trim();
		if(source.matches("^\\d{4}-\\d{1,2}$")){
			return DateUtils.parse(formarts.get(0),value,null);
		}else if(source.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")){
			return DateUtils.parse(formarts.get(1),value,null);
		}else if(source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$")){
			return DateUtils.parse(formarts.get(2),value,null);
		}else if(source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$")){
			return DateUtils.parse(formarts.get(3),value,null);
		}else if(source.matches("^\\d{1,2}:\\d{1,2}:\\d{1,2}$")){
			return DateUtils.parse(formarts.get(4),value,null);
		}else{
			System.err.println("日期格式非法:>>>"+source);
			return null;
		}
	}
}
