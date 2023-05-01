package com.stc.filesystem.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stc.filesystem.constants.Constants;
import com.stc.filesystem.entity.FileItem;
import com.stc.filesystem.entity.Item;
import com.stc.filesystem.entity.Permission;
import com.stc.filesystem.exception.GeneralException;
import com.stc.filesystem.exception.ItemNotFoundException;
import com.stc.filesystem.repository.FileItemRepository;
import com.stc.filesystem.repository.ItemRepository;
import com.stc.filesystem.repository.PermissionRepository;
import com.stc.filesystem.service.abstract_layer.ItemService;

import lombok.RequiredArgsConstructor;

@Service
//why use  @RequiredArgsConstructor
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

	@Autowired
	ItemRepository repo;

	@Autowired
	PermissionRepository permissionRepo;

	@Autowired
	FileItemRepository filerepo;

	@Override
	public List<Item> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item save(Item item, String user_email) {
		// TODO Auto-generated method stub

		boolean validationBoolean = validate(item, user_email);

		if (validationBoolean) {
			Item savedItem = repo.save(item);
			if (savedItem.getType().name().equals(Constants.ItemType.FILE.getValue())) {
				FileItem fileItem = new FileItem();
				byte[] binaryData = getFileArrayOfBytes(savedItem.getItemName()+"testing creating file .");
				fileItem.setItem(savedItem);
				fileItem.setFileText(binaryData);
				//fileItem.setId(10);
				filerepo.save(fileItem);
			}
			return savedItem;
			// check after save
		}
		return null;
	}

	// user_email here belong to parent to check user accessibility on parent
	public boolean validate(Item item, String email) {

		Permission permission = permissionRepo.findByUserEmail(email).get();

		switch (item.getType()) {
		case SPACE:
			return validateOnSpace(item);
		case FOLDER:
			return validateOnFolder(item, permission);
		case FILE:
			return validateOnFile(item, permission);
		default:
			throw new GeneralException("Item Type In Valid!");
		}

	}

	public boolean validateOnSpace(Item space) {
		if (space.getParentId() != null) {
			throw new GeneralException("while creating space it should not have parent");
		}
		return true;
	}

	public boolean validateOnFolder(Item folder, Permission userPermission) {

		Item parent = findById(folder.getParentId()).orElse(null);

		if (folder.getParentId() == null) {
			throw new ItemNotFoundException("for Folder Type parent must exsist ");
		}
		if (parent.getType().equals(Constants.ItemType.FILE.getValue())) {
			throw new GeneralException("for Folder Type parent must not be File type");
		}
		if (!userPermission.getPermissionLevel().name().equals(Constants.PermissionLevel.EDIT.getValue())) {
			throw new GeneralException(" exsisting user have no permission to create folder");
		}
		return true;
	}

	public boolean validateOnFile(Item file, Permission userPermission) {

		if (file.getParentId() == null) {
			throw new ItemNotFoundException("for File Type parent must exsist ");
		}
		if (!userPermission.getPermissionLevel().name().equals(Constants.PermissionLevel.EDIT.getValue())) {
			throw new GeneralException(" exsisting user have no permission to create folder");
		}
		return true;
	}

	public byte[] getFileArrayOfBytes(String fileName) {
		byte[] bytes = null;
		String dataForWriting = "Hi there we are testing create file ";
		File file = new File(fileName);
		try {
			boolean value = file.createNewFile();
			if (value) {
				Files.write(file.toPath(), dataForWriting.getBytes());
				bytes = Files.readAllBytes(file.toPath());
				return bytes;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bytes;
	}

	@Override
	public Optional<Item> findById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}
}
