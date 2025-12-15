package com.tund.identity.service.service;

import com.tund.identity.service.dto.request.UserCreateRequest;
import com.tund.identity.service.dto.request.UserUpdateRequest;
import com.tund.identity.service.entity.User;
import com.tund.identity.service.exception.AppException;
import com.tund.identity.service.exception.ErrorCode;
import com.tund.identity.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(UserCreateRequest request) {
        User user = new User();

        if (userRepository.existsByUserName(request.getUserName())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        user.setUserName(request.getUserName());
        user.setPassword(request.getPassword());
        user.setDisplayName(request.getDisplayName());
        user.setBirthDay(request.getBirthDay());

        return userRepository.save(user);
    }

    public User updateUser(String userId, UserUpdateRequest request) {
        User user = getUser(userId);

        user.setPassword(request.getPassword());
        user.setDisplayName(request.getDisplayName());
        user.setBirthDay(request.getBirthDay());

        return userRepository.save(user);
    }

    public List<User> getListUsers() {
        return userRepository.findAll();
    }

    public User getUser(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
