package currency.converter.exception;

public enum ErrorCode {
	CC_SUCCESS("0", "Success"),
	CC_ERROR("100", "Failure"),
	CC_BADREQUEST("400", "Bad Request"),
	CC_NO_CONVERSION_RATE("101", "No Conversion Rate Found");
	
	private final String code;
	private final String message;
	
	private ErrorCode(final String code, final String message){
		this.code = code;
		this.message= message;
	}
	
	public String getErrorCode(){
		return this.code;
	}
	
	public String getMessage(){
		return this.message;
	}
}
