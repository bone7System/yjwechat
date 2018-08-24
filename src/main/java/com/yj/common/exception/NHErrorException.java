package com.yj.common.exception;

public class NHErrorException extends Exception{
	private static final long serialVersionUID = 3526781918697320427L;
	/**
	 * 构建一个在ui弹出友好错误框的异常
	 * @param warming
	 */
	public NHErrorException(String warming) {
		super("businessLogicError["+warming+"]");
	}
}
