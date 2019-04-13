package com.zsc.base.exception;
import com.zsc.base.vo.RetMsg;

public class SysException extends RuntimeException{
	protected static final long serialVersionUID = 1L;
	protected int errorCode;
	protected String errorInfo;

	public SysException (){}

	public SysException (RetMsg retmsg){
		this.errorCode = retmsg.getRet();
		this.errorInfo = retmsg.getMsg();
	}

	public SysException (String errorInfo){
		this.errorCode = 500;
		this.errorInfo = errorInfo;
	}

	public SysException (int errorCode, String errorInfo){
		this.errorCode = errorCode;
		this.errorInfo = errorInfo;
	}

	@Override
	public String getMessage(){
		return this.errorInfo;
	}

	public int getErrorCode(){
		return errorCode;
	}

	public void setErrorCode(int errorCode){
		this.errorCode = errorCode;
	}

	public String getErrorInfo(){
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo){
		this.errorInfo = errorInfo;
	}
}
