package com.boe.common.exception;

/**
 * 
 * 权限访问异常, 但用户操作无权限进行访问时抛出此异常 
 *
 * @author linjiarong
 * @date 2014年11月2日 下午9:45:51 
 * @version 0.0.1
 *
 */
public class PermissionException extends BaseException{

	/** serialVersionUID */
	private static final long serialVersionUID = 6417641452178955756L;

	/**
     * 构造基础异常
     * @param code 异常代码
     * @param args 异常参数
     * @param defaultMessage 默认异常消息
     * @param cause 引起此异常的触发异常
     */
    public PermissionException(String code, Object[] args, String defaultMessage, Throwable cause) {
    	super(code, args, defaultMessage,cause);
    }
    
    /**
     * 构造基础异常
     * @param code 异常代码
     * @param args 异常参数
     * @param defaultMessage 默认异常消息
     */
    public PermissionException(String code, Object[] args, String defaultMessage) {
    	this(code,args,defaultMessage,null);
    }
    
    /**
     * 构造基础异常
     * @param defaultMessage 默认异常消息
     * @param cause 引起此异常的触发异常
     */
    public PermissionException(String defaultMessage, Throwable cause) {
        this(null, null, defaultMessage, cause);
    }

    /**
     * 构造基础异常
     * @param defaultMessage 默认异常消息
     */
    public PermissionException(String defaultMessage) {
        this(null, null, defaultMessage, null);
    }
    /**
     * 
     * 构造基础异常
     * @param code 异常代码
     * @param args 异常参数
     * @param cause 引起此异常的触发异常
     */
    public PermissionException(String code, Object[] args, Throwable cause) {
        this(code, args, null, cause);
    }

    /**
     * 
     * 构造基础异常
     * @param code 异常代码
     * @param args 异常参数
     */
    public PermissionException(String code, Object[] args) {
        this(code, args, null, null);
    }
}
