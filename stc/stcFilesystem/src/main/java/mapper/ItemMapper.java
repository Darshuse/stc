package mapper;

import com.stc.filesystem.dto.ItemInRsDTO;
import com.stc.filesystem.dto.PermissionGroupRsDto;
import com.stc.filesystem.entity.Item;
import com.stc.filesystem.entity.PermissionGroup;
import com.stc.filesystem.entity.enums.ItemType;

public class ItemMapper {

	public static Item getItemDto(ItemInRsDTO inDto) {
		Item item = new Item();
		PermissionGroup permissionGroup = getPermissionGroupDto(inDto.getPermissionGroup());
		item.setItemName(inDto.getItemName());
		item.setItemPermissionGroup(permissionGroup);
		//item.setId(inDto.getId());
        item.setType(ItemType.valueOf(inDto.getItemType()));
        item.setParentId(inDto.getParentId());
        return item;
	}

	public static PermissionGroup getPermissionGroupDto(PermissionGroupRsDto inDto) {
		PermissionGroup permissionGroup = new PermissionGroup();
		permissionGroup.setGroupName(inDto.getGroupName());
		permissionGroup.setId(inDto.getId());
		return permissionGroup;
	}
}
