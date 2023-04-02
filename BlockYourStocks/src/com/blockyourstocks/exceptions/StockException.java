package com.blockyourstocks.exceptions;

public class StockException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public StockException() {
		
	}
	public StockException(String msg) {
		super(msg);
	}
}
