package com.tund.identity.service.dto.response;

import com.tund.identity.service.entity.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    private String id;
    private String userName;
    private String displayName;
    private LocalDate birthDay;
    Set<Role> roles;
}
