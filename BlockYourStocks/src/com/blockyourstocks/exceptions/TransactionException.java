package com.blockyourstocks.exceptions;

public class TransactionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TransactionException() {

	}

	public TransactionException(String msg) {
		super(msg);
	}
}
