package com.zsc.base.vo;
import com.zsc.base.exception.SysException;
import com.zsc.base.utils.JsonUtils;
/**
 * @author lujun
 * @param <T> 需要返回的对象
 * @param <E> 如果处理异常则返回异常信息,这个异常必须建立在SysException之上
 */
public class RetResult<T,E extends SysException>{
	private boolean success;
	private T value;
	private E exception;
	private Integer ret;

	public RetResult (){}

	@SuppressWarnings("unchecked")
	public RetResult (String errorInfo, boolean success){
		this.value = null;
		this.exception = (E)new SysException(errorInfo);
		this.success = success;
	}

	public RetResult (T value){
		this.value = value;
		this.success = true;
	}

	public RetResult (T value, E exception){
		this.value = value;
		this.exception = exception;
	}

	public RetResult (T param, E exception, boolean success){
		this.value = param;
		this.exception = exception;
		this.success = success;
	}

	@SuppressWarnings("unchecked")
	public RetResult (T param, String errorInfo){
		this.value = param;
		this.exception = (E)new SysException(errorInfo);
		this.success = false;
	}

	public boolean hasError(){
		return success == false;
	}

	public T getValue(){
		return value;
	}

	public void setValue(T value){
		this.value = value;
	}

	public boolean isSuccess(){
		return success;
	}

	public void setSuccess(boolean success){
		this.success = success;
	}

	public E getException(){
		return exception;
	}

	public void setException(E exception){
		this.exception = exception;
	}

	public String getErrorInfo(){
		return this.exception != null?this.exception.getErrorInfo():"未知错误";
	}

	public void setRet(Integer ret){
		this.ret = ret;
	}

	public Integer getRet(){
		return ret;
	}

	@Override
	public String toString(){
		return JsonUtils.toString(this);
	}
}
