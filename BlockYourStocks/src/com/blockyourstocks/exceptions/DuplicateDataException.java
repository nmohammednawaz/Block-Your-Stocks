package com.blockyourstocks.exceptions;

public class DuplicateDataException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public  DuplicateDataException () {
	}
	
	public  DuplicateDataException (String msg) {
		super(msg);
	}
}
