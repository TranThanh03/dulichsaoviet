package com.websitesaoviet.WebsiteSaoViet.dto.response.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AssignmentGuideResponse {
    String id;
    String guideCode;
    String guideName;
    String guideAvatar;
    int evaluate;
    String gender;
    LocalDate dateOfBirth;
    LocalDate startDate;
    LocalDate endDate;
    int numberOfPeople;
    int totalPeople;
    int guidePrice;
}