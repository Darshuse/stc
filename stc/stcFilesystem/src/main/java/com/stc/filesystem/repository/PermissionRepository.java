package com.stc.filesystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stc.filesystem.constants.Constants.PermissionLevel;
import com.stc.filesystem.entity.Permission;

@Repository
@Transactional
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    public Optional<Permission> findByUserEmail(String userEmail);
    public Optional<Permission> findByPermissionGroupIdAndPermissionLevel(Long permissoinID, com.stc.filesystem.entity.enums.PermissionLevel download );
}
