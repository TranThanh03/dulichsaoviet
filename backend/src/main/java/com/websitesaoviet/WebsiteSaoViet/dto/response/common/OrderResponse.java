package com.websitesaoviet.WebsiteSaoViet.dto.response.common;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {
    String id;
    String orderCode;
    String userCode;
    String userName;
    String tourCode;
    String tourName;
    String guideCode;
    String guideName;
    String assignmentId;
    LocalDate startDate;
    LocalDate endDate;
    int tourPrice;
    int guidePrice;
    int numberOfPeople;
    int totalPrice;
    LocalDateTime orderDatetime;
    String status;
}