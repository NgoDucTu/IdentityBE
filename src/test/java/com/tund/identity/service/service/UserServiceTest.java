package com.tund.identity.service.service;

import com.tund.identity.service.dto.request.UserCreateRequest;
import com.tund.identity.service.dto.response.UserResponse;
import com.tund.identity.service.entity.User;
import com.tund.identity.service.exception.AppException;
import com.tund.identity.service.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @MockitoBean
    private UserRepository userRepository;
    private User user;
    private UserCreateRequest request;
    private UserResponse userResponse;
    private LocalDate dob;

    @BeforeEach
    void initData() {
        dob = LocalDate.of(1990, 1, 1);
        request = UserCreateRequest.builder()
                .userName("NameTest")
                .displayName("Test Create")
                .birthDay(dob)
                .password("12345678")
                .build();

        userResponse = UserResponse.builder()
                .id("1232423dfgsga")
                .userName("NameTest")
                .displayName("Test Create")
                .birthDay(dob)
                .build();

        user = User.builder()
                .id("1232423dfgsga")
                .userName("NameTest")
                .displayName("Test Create")
                .birthDay(dob)
                .build();

    }

    @Test
    void createUserValid() {
        //GIVEN
        BDDMockito.given(userRepository.existsByUserName(anyString())).willReturn(false);
        BDDMockito.given(userRepository.save(any())).willReturn(user);

        //WHEN
        var response = userService.createUser(request);

        //THEN
        assertThat(response)
                .isNotNull()
                .extracting("userName", "displayName", "birthDay")
                .containsExactly("NameTest", "Test Create", dob);
    }


    @Test
    void createUserExisted() {
        //GIVEN
        BDDMockito.given(userRepository.existsByUserName(anyString())).willReturn(true);

        //WHEN
        var exception = assertThrows(AppException.class, () -> userService.createUser(request));

        assertThat(exception.getErrorCode().getCode()).isEqualTo(101);
    }
}
