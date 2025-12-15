package com.tund.identity.service.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateRequest {
    @Size(min = 3, message = "INVALID_USERNAME")
    private String userName;

    @Size(min = 8, message = "INVALID_PASSWORD")
    private String password;
    private String displayName;
    private LocalDate birthDay;
}
