package com.websitesaoviet.WebsiteSaoViet.dto.response.admin;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScheduleListResponse {
    String id;
    String code;
    String tourCode;
    LocalDate startDate;
    LocalDate endDate;
    int quantityPeople;
    int totalPeople;
    Double adultPrice;
    Double childrenPrice;
    String status;
}
