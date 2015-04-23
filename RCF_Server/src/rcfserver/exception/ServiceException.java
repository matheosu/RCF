package rcfserver.exception;

import rcfmodel.util.ExceptionUtil;

public class ServiceException extends RuntimeException{

	private static final long serialVersionUID = 8896895508091277456L;
	private static final String NAME_EXCEPTION = "ServiceException";
	
	public ServiceException(String msg){
		super(ExceptionUtil.getMessage(NAME_EXCEPTION, msg));
	}

}
