package com.tund.identity.service.dto.request;

import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class UserCreateRequest {
    @Size(min = 3, message = "INVALID_USERNAME")
    private String userName;

    @Size(min = 8, message = "INVALID_PASSWORD")
    private String password;
    private String displayName;
    private LocalDate birthDay;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }
}
