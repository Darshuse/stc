package com.stc.filesystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stc.filesystem.dto.FileMetaDataDto;
import com.stc.filesystem.entity.FileItem;
import com.stc.filesystem.service.FileItemServiceImpl;

@RestController
@RequestMapping("/file")
public class FileController {

	@Autowired
	FileItemServiceImpl service;
	
	@PostMapping()
	public byte[] downloadFile(@RequestBody FileItem file) {
		return service.downloadFile(file);		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<FileMetaDataDto> getFileMetaData(@PathVariable Long id) {
		FileMetaDataDto fileMetaData = service.getFileMetaData(id);
		return  ResponseEntity.ok(fileMetaData);
	}

}
