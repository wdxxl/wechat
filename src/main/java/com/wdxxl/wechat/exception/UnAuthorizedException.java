package com.wdxxl.wechat.exception;

@SuppressWarnings("serial")
public class UnAuthorizedException extends RuntimeException{
	public static final String ERROR_MESSAGE = "UnAuthorized User Logged In";
	
	/**
	 *	Constructs an exception with the given description.
	 * @param s the exception's description.
	 */
	public UnAuthorizedException(){
		super(ERROR_MESSAGE, null);
	}

	/**
	 * Constructs an exception with the given description and cause.
	 * @param s the exception's description.
     * @param cause the exception's cause.
	 */
	public UnAuthorizedException(Throwable cause) {
		super(ERROR_MESSAGE, cause);
    }

}
