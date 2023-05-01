package com.stc.filesystem.entity;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the item_permission_groups database table.
 * 
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "permission_groups")
@NamedQuery(name = "PermissionGroup.findAll", query = "SELECT i FROM PermissionGroup i")
public class PermissionGroup extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@Column(name = "group_name")
	private String groupName;

}