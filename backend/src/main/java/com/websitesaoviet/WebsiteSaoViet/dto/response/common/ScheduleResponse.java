package com.websitesaoviet.WebsiteSaoViet.dto.response.common;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScheduleResponse {
    String id;
    String code;
    String tourId;
    LocalDate startDate;
    LocalDate endDate;
    int quantityPeople;
    int totalPeople;
    Double adultPrice;
    Double childrenPrice;
    String status;
}
