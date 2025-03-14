package com.websitesaoviet.WebsiteSaoViet.dto.response.admin;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GuideListResponse {
    String guideId;
    String fullName;
    LocalDate dateOfBirth;
    String sex;
    String phone;
    String avatar;
}