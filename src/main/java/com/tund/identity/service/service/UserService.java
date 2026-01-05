package com.tund.identity.service.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tund.identity.service.dto.request.UserCreateRequest;
import com.tund.identity.service.dto.request.UserUpdateRequest;
import com.tund.identity.service.dto.response.UserResponse;
import com.tund.identity.service.entity.User;
import com.tund.identity.service.enums.Role;
import com.tund.identity.service.exception.AppException;
import com.tund.identity.service.exception.ErrorCode;
import com.tund.identity.service.mapper.UserMapper;
import com.tund.identity.service.repository.RoleRepository;
import com.tund.identity.service.repository.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;

    public UserResponse createUser(UserCreateRequest request) {

        log.info("Service: create user");
        if (userRepository.existsByUserName(request.getUserName())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        //        user.setRoles(roles);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request) {
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        ;

        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        var roles = roleRepository.findAllById(request.getRoles());
        user.setRoles(new HashSet<>(roles));

        userMapper.updateUser(user, request);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    //    @PreAuthorize("hasRole('ADMIN')") // check ROLE_ + role
    //    @PreAuthorize("hasAuthority('ROLE_ADMIN')")  // check ROLE_ADMIN
    @PreAuthorize("hasAuthority('READ_DATA')")
    public List<UserResponse> getListUsers() {
        log.info("Admin get all user");
        return userMapper.toListUserRespone(userRepository.findAll());
    }

    @PostAuthorize("returnObject.userName == authentication.name")
    public UserResponse getUser(String userId) {
        log.info("Get Self by user");
        return userMapper.toUserResponse(userRepository
                .findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId)));
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    public UserResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user = userRepository.findByUserName(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return userMapper.toUserResponse(user);
    }
}
