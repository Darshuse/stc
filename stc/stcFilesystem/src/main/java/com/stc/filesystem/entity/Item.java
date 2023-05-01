package com.stc.filesystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import com.stc.filesystem.entity.enums.ItemType;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the item database table.
 * 
 */
@Entity
@Data
@NoArgsConstructor
@NamedQuery(name = "Item.findAll", query = "SELECT i FROM Item i")
public class Item extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "item_name")
	private String itemName;

	// bi-directional many-to-one association to ItemPermissionGroup
	@ManyToOne
	@JoinColumn(name = "permission_group_id")
	private PermissionGroup itemPermissionGroup;

	@Enumerated(EnumType.STRING)
	private ItemType type;
	
	@Column(name = "parent_id")
	private Long parentId;
	
	
	
}