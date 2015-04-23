package rcfclient.exception;

import rcfmodel.util.ExceptionUtil;

public class BeanException extends RuntimeException{

	private static final long serialVersionUID = 5206171786555350254L;
	
	private static final String NAME_EXCEPTION = BeanException.class.getSimpleName();
	
	public BeanException(String msg){
		super(ExceptionUtil.getMessage(NAME_EXCEPTION, msg));
	}

}
