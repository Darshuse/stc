package com.stc.filesystem.exception;

import java.io.Serializable;

public class ItemNotFoundException extends RuntimeException implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ItemNotFoundException(String message) {
		super(message);
	}
}
