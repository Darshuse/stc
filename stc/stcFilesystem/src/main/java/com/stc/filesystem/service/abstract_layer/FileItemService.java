package com.stc.filesystem.service.abstract_layer;

import com.stc.filesystem.dto.FileMetaDataDto;
import com.stc.filesystem.entity.FileItem;

public interface FileItemService {

	public FileItem save(FileItem file);

	public byte[] downloadFile(FileItem file);
	
	
	public FileMetaDataDto getFileMetaData(Long fileId);

}
