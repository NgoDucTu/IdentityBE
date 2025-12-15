package com.tund.identity.service.controller;

import com.tund.identity.service.dto.request.ApiResponse;
import com.tund.identity.service.dto.request.UserCreateRequest;
import com.tund.identity.service.dto.request.UserUpdateRequest;
import com.tund.identity.service.dto.response.UserResponse;
import com.tund.identity.service.entity.User;
import com.tund.identity.service.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    private UserService userService;

    @PostMapping("/create")
    ApiResponse<User> createUser(@RequestBody @Valid UserCreateRequest request) {
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(request));
        return apiResponse;
    }

    @GetMapping("/get")
    List<User> getListUsers() {
        return userService.getListUsers();
    }

    @GetMapping("/get/{userId}")
    UserResponse getUser(@PathVariable("userId") String userId) {
        return userService.getUser(userId);
    }

    @PutMapping("/update/{userId}")
    UserResponse updateUser(@PathVariable("userId") String userId, @RequestBody UserUpdateRequest request) {
        return userService.updateUser(userId, request);
    }

    @DeleteMapping("/delete/{userId}")
    String deleteUser(@PathVariable("userId") String userId) {
        userService.deleteUser(userId);
        return ("Deleted user with Id: " + userId);
    }
}
