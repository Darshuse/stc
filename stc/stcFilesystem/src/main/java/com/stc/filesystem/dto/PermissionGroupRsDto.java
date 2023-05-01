package com.stc.filesystem.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class PermissionGroupRsDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String groupName;

}
