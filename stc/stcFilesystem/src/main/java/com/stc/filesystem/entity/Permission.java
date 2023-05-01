package com.stc.filesystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.stc.filesystem.entity.enums.PermissionLevel;

import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the permission_groups database table.
 * 
 */
@Entity
@Data
@NoArgsConstructor
@Table(name="permission")
@NamedQuery(name="Permission.findAll", query="SELECT p FROM Permission p")
public class Permission extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@Enumerated(EnumType.STRING)
	private PermissionLevel permissionLevel;

	@Column(name="user_email")
	private String userEmail;

	//bi-directional many-to-one association to ItemPermissionGroup
	@ManyToOne
	@JoinColumn(name="group_id")
	private PermissionGroup permissionGroup;

	
}