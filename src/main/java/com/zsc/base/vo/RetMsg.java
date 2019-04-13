package com.zsc.base.vo;
import java.io.Serializable;
/**
 * 消息传递中间对象 功能说明：<br>
 * 模块名称：Base<br>
 * 功能描述：RetMsg<br>
 * 文件名称: RetMsg.java<br>
 * 系统名称：ICELOVE<br>
 * 软件著作权：ICELOVE 版权所有<br>
 * 开发人员：lujun <br>
 * 开发时间：2018-5-14 下午3:58:10<br>
 * 系统版本：1.0.0<br>
 */
public class RetMsg implements Serializable{
	private static final long serialVersionUID = 1L;
	public final static int RET_SUCCESS = 0;
	public final static int RET_ERROR   = 1;
	protected int ret;
	protected String msg;

	public RetMsg (){}

	public RetMsg (int ret, String msg){
		super();
		this.ret = ret;
		this.msg = msg;
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
}
