package com.wdxxl.wechat.exception;

@SuppressWarnings("serial")
public class WechatException extends RuntimeException{
	public static final String ERROR_MESSAGE = "Wechat Exception Occur";
	
	/**
	 *Constructs an exception with the given description.
	 * @param s the exception's description.
	 */
	public WechatException(){
		super(ERROR_MESSAGE, null);
	}

	/**
	 * Constructs an exception with the given description and cause.
	 * @param s the exception's description.
     * @param cause the exception's cause.
	 */
	public WechatException(Throwable cause) {
		super(ERROR_MESSAGE, cause);
    }

}
