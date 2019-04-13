package com.zsc.base.abs;
import com.github.pagehelper.PageInfo;
import com.zsc.base.exception.SysException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class BaseService{
	protected Log logger = LogFactory.getLog(getClass());
	protected void throwError(String errorInfo) throws SysException {
		throw new SysException(errorInfo);
	}

}

