package com.tund.identity.service.mapper;

import com.tund.identity.service.dto.request.PermissionRequest;
import com.tund.identity.service.dto.response.PermissionResponse;
import com.tund.identity.service.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
