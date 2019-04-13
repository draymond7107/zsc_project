package com.zsc.base.spring;
import com.zsc.base.utils.JsonUtils;
import com.zsc.base.vo.RetMsg;

import java.io.Serializable;
/**
 * 带数据的返回对象
 * 功能说明：<br>
 * 模块名称：NRBase<br>
 * 功能描述：JsonResult<br>
 * 文件名称: JsonResult.java<br>
 * 系统名称：ICELOVE<br>
 * 软件著作权：ICELOVE 版权所有<br>
 * 开发人员：lujun <br>
 * 开发时间：2018-5-14 下午4:15:32<br>
 * 系统版本：1.0.0<br>
 */
public class JsonResult extends RetMsg implements Serializable{
	private static final long serialVersionUID = 1L;
	private Object data;
	public JsonResult (){}
	public JsonResult (RetMsg rm){
		this.ret = rm.getRet();
		this.msg = rm.getMsg();
	}
	public JsonResult (int ret, String msg){
		this.ret = ret;
		this.msg = msg;
	}
	public JsonResult(boolean success){
		this.ret=success?RET_SUCCESS:RET_ERROR;
		this.msg = success?"成功":"失败";
	}
	public JsonResult(boolean success,String msg){
		this.ret = success?RET_SUCCESS:RET_ERROR;
		this.msg = msg;
	}
	public JsonResult(boolean success,String msg,Object data){
		this.ret = success?RET_SUCCESS:RET_ERROR;
		this.msg = msg;
		this.data = data;
	}
	public JsonResult success(){
		this.ret=RET_SUCCESS;
		this.msg = "成功";
		return this;
	}
	public JsonResult success(String msg){
		this.ret=RET_SUCCESS;
		this.msg = msg;
		return this;
	}
	public JsonResult error(String msg){
		this.ret=RET_ERROR;
		this.msg = msg;
		return this;
	}
	public int getRet(){
		return ret;
	}
	public void setRet(int ret){
		this.ret = ret;
	}
	public String getMsg(){
		return msg;
	}
	public void setMsg(String msg){
		this.msg = msg;
	}
	public Object getData(){
		return data;
	}
	public void setData(Object data){
		this.data = data;
	}
	@Override
	public String toString(){
		return JsonUtils.toStringNoEx(this);
	}
}
