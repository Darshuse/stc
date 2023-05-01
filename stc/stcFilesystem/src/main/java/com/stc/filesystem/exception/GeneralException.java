package com.stc.filesystem.exception;

import java.io.Serializable;

public class GeneralException   extends RuntimeException implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GeneralException(String message) {
		super(message);
	}
}
