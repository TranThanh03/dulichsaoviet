package com.websitesaoviet.WebsiteSaoViet.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GuideResponse {
    String id;
    String fullName;
    String avatar;
    String sex;
    LocalDate dateOfBirth;
    String phone;
    String email;
    String description;
    int evaluate;
}