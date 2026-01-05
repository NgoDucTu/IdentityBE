package com.tund.identity.service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.tund.identity.service.dto.request.RoleRequest;
import com.tund.identity.service.dto.response.ApiResponse;
import com.tund.identity.service.dto.response.RoleResponse;
import com.tund.identity.service.service.RoleService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoleController {
    RoleService roleService;

    @PostMapping("/create")
    ApiResponse<RoleResponse> create(@RequestBody RoleRequest request) {
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.create(request))
                .build();
    }

    @GetMapping("/getlist")
    ApiResponse<List<RoleResponse>> getList() {
        return ApiResponse.<List<RoleResponse>>builder()
                .result(roleService.getList())
                .build();
    }

    @DeleteMapping("/delete/{roleName}")
    ApiResponse<Void> delete(@PathVariable String roleName) {
        roleService.delete(roleName);
        return ApiResponse.<Void>builder().build();
    }
}
