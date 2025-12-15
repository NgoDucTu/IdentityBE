package com.tund.identity.service.controller;

import com.tund.identity.service.dto.request.UserCreateRequest;
import com.tund.identity.service.dto.request.UserUpdateRequest;
import com.tund.identity.service.entity.User;
import com.tund.identity.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    User createUser(@RequestBody UserCreateRequest request) {
        return userService.createUser(request);
    }

    @GetMapping("/get")
    List<User> getListUsers() {
        return userService.getListUsers();
    }

    @GetMapping("/get/{userId}")
    User getUser(@PathVariable("userId") String userId) {
        return userService.getUser(userId);
    }

    @PutMapping("/update/{userId}")
    User updateUser(@PathVariable("userId") String userId,@RequestBody UserUpdateRequest request) {
        return userService.updateUser(userId,request);
    }

    @DeleteMapping("/delete/{userId}")
    String deleteUser(@PathVariable("userId") String userId) {
        userService.deleteUser(userId);
        return ("Deleted user with Id: " + userId);
    }
}
