package com.tund.identity.service.controller;

import com.tund.identity.service.dto.request.PermissionRequest;
import com.tund.identity.service.dto.response.ApiResponse;
import com.tund.identity.service.dto.response.PermissionResponse;
import com.tund.identity.service.service.PermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permission")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PermissionController {
    PermissionService permissionService;

    @PostMapping("/create")
    ApiResponse<PermissionResponse> create(@RequestBody PermissionRequest request) {
        return ApiResponse.<PermissionResponse>builder()
                .result(permissionService.create(request))
                .build();
    }

    @GetMapping("/getlist")
    ApiResponse<List<PermissionResponse>> getList() {
        return ApiResponse.<List<PermissionResponse>>builder()
                .result(permissionService.getList())
                .build();
    }


    @DeleteMapping("/delete/{permissionName}")
    ApiResponse<Void> delete(@PathVariable("permissionName") String permissionName) {
        permissionService.delete(permissionName);
        return ApiResponse.<Void>builder()
                .build();
    }
}
