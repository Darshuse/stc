package com.stc.filesystem.dto;

import lombok.Data;


public interface FileMetaDataDto {

	String getItemType();
	String getFileName();
	String getFolderName();
	String getSpaceName();

}
