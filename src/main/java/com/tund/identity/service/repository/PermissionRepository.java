package com.tund.identity.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tund.identity.service.entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {}
