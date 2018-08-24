package com.yj.common.exception;

public class NHWarmingException extends Exception{
	private static final long serialVersionUID = 3526781918697320427L;
	/**
	 * 构建一个在ui弹出友好警示框的异常
	 * @param warming
	 */
	public NHWarmingException(String warming) {
		super("businessLogicWarm["+warming+"]");
	}
	
}
