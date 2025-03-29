package com.websitesaoviet.WebsiteSaoViet.dto.response.common;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AssignmentResponse {
    String id;
    String code;
    String tourCode;
    String guideCode;
    LocalDate startDate;
    LocalDate endDate;
    int numberOfPeople;
    int totalPeople;
    int guidePrice;
    String status;
}