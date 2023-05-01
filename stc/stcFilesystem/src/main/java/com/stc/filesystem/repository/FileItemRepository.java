package com.stc.filesystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stc.filesystem.dto.FileMetaDataDto;
import com.stc.filesystem.entity.FileItem;

@Repository
@Transactional
public interface FileItemRepository extends JpaRepository<FileItem, Long>{

	  @Query(value=""
	    		+ "SELECT i.TYPE AS itemType, i.item_name AS fileName, "
	    		+ "CASE WHEN i3.item_name IS NULL THEN NULL ELSE i2.item_name end as folderName, "
	    		+ "CASE WHEN i3.item_name IS NULL THEN i2.item_name ELSE i3.item_name END AS spaceName "
	    		+ "FROM file_item f "
	    		+ "JOIN item i ON i.id = f.item_id "
	    		+ "JOIN item i2 ON i2.id = i.parent_id "
	    		+ "LEFT JOIN item i3 ON i3.id = i2.parent_id "
	    		+ "WHERE f.id = :fileId"
	    		, nativeQuery=true)
		Optional<FileMetaDataDto> getFileMetaData(Long fileId);
}
