package com.stc.filesystem.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stc.filesystem.entity.enums.PermissionLevel;
import com.stc.filesystem.dto.FileMetaDataDto;
import com.stc.filesystem.entity.FileItem;
import com.stc.filesystem.entity.Permission;
import com.stc.filesystem.exception.GeneralException;
import com.stc.filesystem.repository.FileItemRepository;
import com.stc.filesystem.repository.PermissionRepository;
import com.stc.filesystem.service.abstract_layer.FileItemService;

@Service
public class FileItemServiceImpl implements FileItemService{

	
	@Autowired
	FileItemRepository repo;
	@Autowired
	PermissionRepository permissionRepo;
	
	@Override
	public FileItem save(FileItem file) {
		// TODO Auto-generated method stub
		return repo.save(file);
	}

	@Override
	public byte[] downloadFile(FileItem file) {
		// TODO Auto-generated method stub
		Optional<FileItem> dbFile = repo.findById(file.getId());
		if(!dbFile.isPresent()) {
			throw new GeneralException("File Not Found");
		}
		
		Long permissoinID = dbFile.get().getItem().getItemPermissionGroup().getId();
		Optional<Permission> userPermission = permissionRepo.findByPermissionGroupIdAndPermissionLevel(permissoinID, PermissionLevel.DAWNLOAD);
		if (!userPermission.isPresent()) {
			throw new GeneralException("you do not have a permisson to download this file!");
		}
		
		return dbFile.get().getFileText();
	}

	@Override
	public FileMetaDataDto getFileMetaData(Long fileId) {
		Optional<FileMetaDataDto> fileMetaData = repo.getFileMetaData(fileId);
		if (!fileMetaData.isPresent()) {
			throw new GeneralException("File Not Found");
		}
		
		return fileMetaData.get();
	}

}
