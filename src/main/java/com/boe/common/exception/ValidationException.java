package com.boe.common.exception;

/**
 * 
 * 校验异常, 对业务对象,传入参数,传出参数进行校验时,发现不合法值可抛出此异常  
 *
 * @author linjiarong
 * @date 2014年11月2日 下午9:46:46 
 * @version 0.0.1
 *
 */
public class ValidationException extends BaseException{

	/** serialVersionUID */
	private static final long serialVersionUID = 6417641452178955756L;

	/**
     * 构造基础异常
     * @param code 异常代码
     * @param args 异常参数
     * @param defaultMessage 默认异常消息
     * @param cause 引起此异常的触发异常
     */
    public ValidationException(String code, Object[] args, String defaultMessage, Throwable cause) {
    	super(code, args, defaultMessage,cause);
    }
    
    /**
     * 构造基础异常
     * @param code 异常代码
     * @param args 异常参数
     * @param defaultMessage 默认异常消息
     */
    public ValidationException(String code, Object[] args, String defaultMessage) {
    	this(code, args, defaultMessage,null);
    }
    
    /**
     * 构造基础异常
     * @param defaultMessage 默认异常消息
     * @param cause 引起此异常的触发异常
     */
    public ValidationException(String defaultMessage, Throwable cause) {
        this(null, null, defaultMessage, cause);
    }

    /**
     * 构造基础异常
     * @param defaultMessage 默认异常消息
     */
    public ValidationException(String defaultMessage) {
        this(null, null, defaultMessage, null);
    }
    /**
     * 
     * 构造基础异常
     * @param code 异常代码
     * @param args 异常参数
     * @param cause 引起此异常的触发异常
     */
    public ValidationException(String code, Object[] args, Throwable cause) {
        this(code, args, null, cause);
    }

    /**
     * 
     * 构造基础异常
     * @param code 异常代码
     * @param args 异常参数
     */
    public ValidationException(String code, Object[] args) {
        this(code, args, null, null);
    }
}
