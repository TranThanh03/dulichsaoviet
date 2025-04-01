package com.websitesaoviet.WebsiteSaoViet.dto.response.common;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerResponse {
    String id;
    String code;
    String fullName;
    String phone;
    String email;
    LocalDateTime registeredTime;
    String status;
}
