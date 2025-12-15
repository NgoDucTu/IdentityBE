package com.tund.identity.service.service;

import com.tund.identity.service.dto.request.UserCreateRequest;
import com.tund.identity.service.dto.request.UserUpdateRequest;
import com.tund.identity.service.dto.response.UserResponse;
import com.tund.identity.service.entity.User;
import com.tund.identity.service.exception.AppException;
import com.tund.identity.service.exception.ErrorCode;
import com.tund.identity.service.mapper.UserMapper;
import com.tund.identity.service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;

    public User createUser(UserCreateRequest request) {
        if (userRepository.existsByUserName(request.getUserName())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        User user = userMapper.toUser(request);
        return userRepository.save(user);
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        ;
        userMapper.updateUser(user, request);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    public List<User> getListUsers() {
        return userRepository.findAll();
    }

    public UserResponse getUser(String userId) {
        return userMapper.toUserResponse(userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId)));
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
