package com.websitesaoviet.WebsiteSaoViet.dto.response.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AssignmentTourResponse {
    String id;
    String tourId;
    String tourName;
    String tourImage;
    int tourPrice;
    LocalDate startDate;
    LocalDate endDate;
    int numberOfPeople;
    int totalPeople;
    int guidePrice;
}