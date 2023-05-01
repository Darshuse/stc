package com.stc.filesystem.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class ItemInRsDTO  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	private String itemName;
	private PermissionGroupRsDto permissionGroup;
	private String itemType;
	private String user_email;
	private Long parentId;

}
