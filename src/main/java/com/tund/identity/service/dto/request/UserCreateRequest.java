package com.tund.identity.service.dto.request;

import com.tund.identity.service.validator.BirthDayAnnotation;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserCreateRequest {
    @Size(min = 3, message = "INVALID_USERNAME")
    private String userName;

    @Size(min = 8, message = "INVALID_PASSWORD")
    private String password;
    private String displayName;
    @BirthDayAnnotation(min = 18, message = "INVALID_DOB")
    private LocalDate birthDay;
}
