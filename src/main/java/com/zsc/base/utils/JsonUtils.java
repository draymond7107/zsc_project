package com.zsc.base.utils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class JsonUtils{
	public static final SerializeConfig SERIALIZE_CONFIG = new SerializeConfig();
	public static void main(String[] args){
		System.out.println(parse("{root:{id2875:'color_canyd',id2876:'color_canyd',id2877:'color_canyd',id2878:'color_canyd',id2879:'color_canyd',id2880:'color_canyd'}}"));
	}
	/**
	 * 解析字符串到JSON对象
	 * @param string
	 * @return
	 */
	public static final Object parse(String string){
		return JSON.parse(string);
	}
	/**
	 * javaObject >>> JsonObject
	 * @param javaObject
	 * @return
	 */
	public static final JSONObject toJSON(Object javaObject){
		return (JSONObject)JSON.toJSON(javaObject);
	}
	public static final JSONObject parseString(String jsonString){
		if(jsonString==null)return null;
		return JSON.parseObject(jsonString);
	}
	public static final JSONObject asJSONObject(String jsonString){
		return JSON.parseObject(jsonString);
	}
	/**
	 * 扑捉了一次的方法
	 * @param jsonString
	 * @return 如果异常返回def
	 */
	public static final JSONObject asJSONObject(String jsonString,JSONObject def){
		try{
			if(jsonString==null || jsonString.length()==0 || !jsonString.startsWith("{"))return def;
			return JSON.parseObject(jsonString);
		}catch (Exception e) {
			return def;
		}
	}
	public static final JSONArray asJSONArray(String jsonString){
		return JSON.parseArray(jsonString);
	}
	/**
	 * 将 list 对象 >>> JsonObject
	 * @param list
	 * @return
	 */
	public static final JSONArray toJSONArray(List<?> list){
		return (JSONArray)JSON.toJSON(list);
	}
	/**
	 * JsonObject >>> javaObject
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static final <T>T toJavaObject(JSON json,Class<T> clazz){
		return JSON.toJavaObject(json,clazz);
	}
	public static final <T>T toJavaObject(String text,Class<T> clazz){
		return JSON.parseObject(text,clazz);
	}
	public static final Map<String,String> fromJsonToMap(String json) throws Exception{
		Map<String,Object> map = JSON.parseObject(json);
		Map<String,String> retMap = new HashMap<String,String>();
		for(String key:map.keySet()){
			retMap.put(key,String.valueOf(map.get(key)));
		}
		return retMap;
	}
	/**
	 * 封装将json对象转换为java集合对象
	 * @param <T>
	 * @param clazz List对象的类 如 User.class
	 * @param jsons
	 * @return
	 */
	public static final <T>List<T> getJavaCollection(String jsons,Class<T> clazz){
		JSONArray array = JSON.parseArray(jsons);
		return jsonArrayToList(array,clazz);
	}
	/**
	 * JSONArray >>> List<T> 等同于 jsonArrayToList
	 * @param array json数组
	 * @param clazz List对象的类 如 User.class
	 * @return
	 * @throws Exception
	 */
	public static final <T>List<T> jsonArrayToList(JSONArray array,Class<T> clazz){
		List<T> objs = null;
		if(array != null){
			objs = new ArrayList<T>();
			for(int i = 0;i < array.size();i++){
				JSONObject jsonObject = array.getJSONObject(i);
				T obj = JSON.toJavaObject(jsonObject,clazz);
				objs.add(obj);
			}
		}
		return objs;
	}
	/**
	 * JSONArray >>> List<T> 等同于 jsonArrayToList
	 * @param array json数组
	 * @param clazz List对象的类 如 User.class
	 * @return
	 */
	public static final <T>List<T> toJavaList(JSONArray array,Class<T> clazz){
		return jsonArrayToList(array,clazz);
	}
	/**
	 * 对象转json字符串
	 * @param object
	 * @return
	 */
	public static final String toString(Object object){
		// 两种写法
		return JSON.toJSONString(object,SerializerFeature.WriteDateUseDateFormat,SerializerFeature.IgnoreNonFieldGetter);
		// return JSON.toJSONString(object,SERIALIZE_CONFIG);
	}
	/**
	 * 转JSON捕获异常的
	 * @param object
	 * @return
	 */
	public static final String toStringNoEx(Object object){
		try{
			return JSON.toJSONString(object,SerializerFeature.WriteDateUseDateFormat);
		}catch(Exception e){
			return "format json error";
		}
	}
	public static final boolean isBadJson(String json){
		return !isJson(json);
	}
	/**
	 * 最好不要用这个方法，直接转，如果异常就不是JSON了
	 * @param json
	 * @return
	 */
	public static final boolean isJson(String json){
		if(StringUtils.isEmpty(json)){
			return false;
		}
		try{
			JSON.parse(json);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	public static final boolean isJsonSimple(String json){
		if(StringUtils.isEmpty(json)){
			return false;
		}
		return json.startsWith("{") && json.endsWith("}");
	}
	
	public static final JSONObject create(String[] keys, Serializable[] values){
		JSONObject model = new JSONObject();
		if(keys==null || values==null || keys.length!=values.length){
			return model;
		}
		for(int i=0;i<keys.length;i++){
			model.put(keys[i],values[i]);
		}
		return model;
	}
}
