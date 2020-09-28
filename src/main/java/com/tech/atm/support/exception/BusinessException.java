package com.tech.atm.support.exception;

import com.tech.atm.domain.logic.BusinessMessage;

public class BusinessException extends Exception {

	private static final long serialVersionUID = 1L;
	private BusinessMessage businessMessage;

	/**
	 * Exception constructor.
	 * @param msg exception message
	 */
	public BusinessException(String msg) {
		super(msg);
	}

	/**
	 * Nesting constructor.
	 * @param cause the causing exception
	 */
	public BusinessException(Throwable cause) {
		super(cause);
	}

	/**
	 * Nesting constructor.
	 * @param message - the error message
	 * @param cause - the causing exception
	 */
	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(BusinessMessage businessMessage) {
		this.businessMessage = businessMessage;
	}

	public BusinessMessage getBusinessMessage() {
		return businessMessage;
	}

	public void setBusinessMessage(BusinessMessage businessMessage) {
		this.businessMessage = businessMessage;
	}
}
