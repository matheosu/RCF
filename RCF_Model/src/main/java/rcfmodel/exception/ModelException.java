package rcfmodel.exception;

import rcfmodel.util.ExceptionUtil;

public class ModelException extends RuntimeException{

	private static final long serialVersionUID = -557879291421098229L;
	private static final String NAME_EXCEPTION = ModelException.class.getSimpleName();
	
	public ModelException(String msg){
		super(ExceptionUtil.getMessage(NAME_EXCEPTION,msg));
	}

}
